package com.community.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.community.controller.BoardController;
import com.community.service.BoardService;
import com.community.vo.BoardVO;
import com.community.vo.PagingVO;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	private static Logger log = LoggerFactory.getLogger(BoardController.class);
	private BoardService boardservice;
	
	@Autowired
	public BoardController(BoardService boardservice) {
		this.boardservice = boardservice;
	}
	
	/* 페이지 요청 */
	// 게시글 검색 페이지
	@GetMapping("/list")
	public String boardSearch(@RequestParam("btype") String board_type,
			@RequestParam(value = "no", required = false) Integer board_no,
			@RequestParam(value = "keytype", required = false) String keyword_type,
			@RequestParam(value = "keyword", required = false) String keyword,
			@RequestParam(value = "page", required = false) Integer curPage) throws Exception {

		String board_name = boardservice.findBoardName(board_type);

		if (board_name == "null") {
			// 게시판 리스트로 수정해야함
			log.info("Linked to MainPage");
			return "redirect:/";
		}

		if (board_no != null) {
			boardservice.addBoardViews(board_no);
			log.info("Linked to DetailPage");
			return "/Board/DetailPage";
		}

		log.info("Linked to SearchPage");
		return "Board/SearchPage";

	}
	
	// regist + modify = write & param transfer post
	// 게시글 등록 페이지
	@GetMapping("/write")
	public String boardWrite(@RequestParam("btype") String board_type) throws Exception {

		log.info("Linked to WritePage");
		return boardservice.findBoardName(board_type) != "null" ? "Board/RegistPage" : "redirect:/";

	}

	// DB 요청
	// 게시판 이름 정보 요청
	@GetMapping("/name.do")
	@ResponseBody
	public ResponseEntity<String> boardName(@RequestParam("btype") String board_type) throws Exception {

		log.info("Requesting to read a board's name on server ");
		
		return new ResponseEntity<>(boardservice.findBoardName(board_type), HttpStatus.OK);
	}
	
	// 게시글 리스트 & 페이징 정보 요청
	@GetMapping("/list.do")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> boardList(@RequestParam("btype") String board_type,
			@RequestParam("keytype") String keyword_type, @RequestParam("keyword") String keyword,
			@RequestParam("page") String curPage) throws Exception {

		int page = curPage != null && curPage != ""
				? curPage.chars().allMatch(Character::isDigit) == true ? Integer.parseInt(curPage) : 1 : 1;
		
		PagingVO pagingVO = new PagingVO(boardservice.findBoardTotal(board_type, keyword_type, keyword), 10, page);
		List<BoardVO> BoardList = boardservice.findBoardList(board_type, keyword_type, keyword, pagingVO);

		Map<String, Object> result = new HashMap<String, Object>();

		result.put("list", BoardList);
		result.put("paging", pagingVO);

		log.info("Requesting to read a board's list & paging info on server ");
		
		return new ResponseEntity<>(result, HttpStatus.OK);

	}
	
	// 게시글 정보 요청
	@PostMapping("/detail.do")
	@ResponseBody
	public ResponseEntity<BoardVO> boardDetail(@RequestParam("bno") int board_no) throws Exception {

		log.info("Requesting to read a board on server ");
		
		return new ResponseEntity<>(boardservice.findBoard(board_no), HttpStatus.OK);
	}
	
	// 게시글 등록 및 갱신
	@PostMapping("/write.do")
	public ResponseEntity<Integer> boardWrite(@RequestBody BoardVO boardVO) throws Exception {

		boardservice.addBoard(boardVO);

		log.info("Requesting to create a board on server ");

		return new ResponseEntity<>(boardVO.getBoard_no(), HttpStatus.OK);

	}

	// 게시글 삭제
	@GetMapping("/remove.do")
	@ResponseBody
	public ResponseEntity<Integer> boardRemove(@RequestParam("btype") String board_type, @RequestParam("bno") int board_no) throws Exception {

		log.info("Requesting to delete a board on server ");
		
		return new ResponseEntity<>(boardservice.removeBoard(board_type, board_no), HttpStatus.OK);

	}
	
}
