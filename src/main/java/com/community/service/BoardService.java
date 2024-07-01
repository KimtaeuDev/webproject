package com.community.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.community.vo.BoardVO;
import com.community.vo.PagingVO;
import com.community.mapper.BoardMapper;

@Service
public class BoardService {

	private BoardMapper boardmapper;

	@Autowired
	public BoardService(BoardMapper boardmapper) {
		this.boardmapper = boardmapper;
	}

	// 게시판 이름 확인
	public String findBoardName(String board_type) throws Exception {

		return boardmapper.selectBoardName(board_type);
	}

	// 글 조회수 증가
	public int addBoardViews(int board_no) throws Exception {

		return boardmapper.updateBoardViews(board_no);

	}

	// 글 총 개수 얻기
	public int findBoardTotal(String board_type, String keyword_type, String keyword) throws Exception {

		return boardmapper.selectBoardTotal(board_type, keyword_type, keyword);

	}

	// 글 리스트 얻기
	public ArrayList<BoardVO> findBoardList(String board_type, String keyword_type, String keyword, PagingVO pagingVO)
			throws Exception {

		Map<String, Object> boardmap = new HashMap<String, Object>();

		boardmap.put("board_type", board_type);
		boardmap.put("keyword_type", keyword_type);
		boardmap.put("keyword", keyword);
		boardmap.put("startRow", pagingVO.getStartRow());
		boardmap.put("endRow", pagingVO.getEndRow());

		return boardmapper.selectBoardList(boardmap);
	}

	// 글 읽기
	public BoardVO findBoard(int board_no) throws Exception {

		return boardmapper.selectBoard(board_no);
	}

	// 글 등록 및 갱신
	public int addBoard(BoardVO boardVO) throws Exception {

		return boardmapper.insertBoard(boardVO);

	}

	// 글 삭제
	public int removeBoard(String board_type, int board_no) throws Exception {

		return boardmapper.deleteBoard(board_type, board_no);
	}

}
