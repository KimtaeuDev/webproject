package com.community.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.community.vo.PagingVO;
import com.community.vo.ReplyVO;

import jakarta.persistence.EntityManager;

import com.community.service.ReplyService;

@Controller
@RequestMapping("/reply/*")
public class ReplyController {

	private static Logger log = LoggerFactory.getLogger(ReplyController.class);
	private ReplyService replyservice;
	private EntityManager em;
	
	@Autowired
	public ReplyController(ReplyService replyservice) {
		this.replyservice = replyservice;
	}

	// 댓글 리스트 & 페이징 정보 얻기
	@ResponseBody
	@PostMapping("/list.do")
	public ResponseEntity<Map<String, Object>> getReplyList(@RequestParam("bno") String board_no,
			@RequestParam("btype") String board_type, @RequestParam("order") String reply_order,
			@RequestParam("page") String replyPage) throws Exception {
		
		int bno = board_no.chars().allMatch(Character::isDigit) == true ? Integer.parseInt(board_no) : 0;
		int page = replyPage.chars().allMatch(Character::isDigit) == true ? Integer.parseInt(replyPage) : 1;

		if (bno == 0) {
			return null;
		}
		PagingVO pagingVO = replyservice.findReplyPaging(bno, board_type, page);

		Map<String, Object> result = new HashMap<String, Object>();

		result.put("list", replyservice.findReplyList(bno, board_type, reply_order, pagingVO));
		result.put("paging", pagingVO);

		log.info("getReplyList in progress...");
		return new ResponseEntity<>(result, HttpStatus.OK);

	}

	// 댓글 생성 및 수정 
	@ResponseBody
	@RequestMapping(value = { "/write.do" }, method = RequestMethod.GET)
	public  ResponseEntity<Integer> writeReply(@RequestBody ReplyVO replyVO) throws Exception {
		// 로그인 정보 확인 필요 
		return new ResponseEntity<>(replyservice.addReply(replyVO), HttpStatus.OK);
	}

	// 댓글 삭제
	@ResponseBody
	@RequestMapping(value = { "/remove.do" }, method = RequestMethod.GET)
	public ResponseEntity<Integer> removeReply(@RequestParam("bno") int board_no, @RequestParam("btype") String board_type,
			@RequestParam("rno") int reply_no) throws Exception {
		return new ResponseEntity<>(replyservice.removeReply(board_no, board_type, reply_no), HttpStatus.OK);
	}

}
