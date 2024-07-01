package com.community.mapper;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.community.vo.BoardVO;

@Mapper
public interface BoardMapper {

	// 게시판 이름 확인
	public String selectBoardName(String board_type) throws Exception;

	// 게시글 조회수 증가
	public int updateBoardViews(int board_no) throws Exception;

	// 글 총 개수 얻기
	public int selectBoardTotal(@Param("board_type") String board_type, @Param("keyword_type") String keyword_type,
			@Param("keyword") String keyword) throws Exception;

	// 글 리스트 얻기
	public ArrayList<BoardVO> selectBoardList(Map<String, Object> boardmap) throws Exception;

	// 글 읽기
	public BoardVO selectBoard(int board_no) throws Exception;
	
	// 글 등록
	public int insertBoard(BoardVO boardVO) throws Exception;

	// 글 삭제
	public int deleteBoard(@Param("board_type") String board_type, @Param("board_no") int board_no) throws Exception;

}
