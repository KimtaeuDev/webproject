package com.community.service;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.community.vo.PagingVO;
import com.community.vo.ReplyVO;
import com.community.mapper.ReplyMapper;

@Service
public class ReplyService {

	private ReplyMapper replymapper;

	@Autowired
	public ReplyService(ReplyMapper replymapper) {
		this.replymapper = replymapper;
	}

	// 댓글 개수 확인
	public int findReplyCount(String board_type, int board_no) throws Exception {

		return replymapper.selectReplyCount(board_type, board_no);

	}

	// 게시글 페이징 불러오기
	public PagingVO findReplyPaging(int board_no, String board_type, int curPage) throws Exception {

		return new PagingVO(findReplyCount(board_type, board_no), 10, curPage);

	}

	// 댓글 리스트 불러오기
	public ArrayList<ReplyVO> findReplyList(int board_no, String board_type, String reply_order, PagingVO pagingVO)
			throws Exception {

		Map<String, Object> replymap = new HashMap<String, Object>();

		replymap.put("board_no", board_no);
		replymap.put("board_type", board_type);
		replymap.put("reply_order", reply_order);
		replymap.put("startRow", pagingVO.getStartRow());
		replymap.put("endRow", pagingVO.getEndRow());

		return replymapper.selectReplyList(replymap);
	}

	// 댓글 생성 및 수정
	public int addReply(ReplyVO replyVO) throws Exception {

		return replymapper.insertReply(replyVO);
	}

	// 댓글 삭제
	public int removeReply(int board_no, String board_type, int reply_no) throws Exception {

		return replymapper.deleteReply(board_no, board_type, reply_no);
	}

}
