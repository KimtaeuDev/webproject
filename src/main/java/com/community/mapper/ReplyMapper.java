package com.community.mapper;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.community.vo.ReplyVO;

@Mapper
public interface ReplyMapper {

	public int selectReplyCount(@Param("board_type") String board_type, @Param("board_no") int board_no) throws Exception;

	public ArrayList<ReplyVO> selectReplyList(Map<String, Object> boardmap) throws Exception;

	public int insertReply(ReplyVO replyVO) throws Exception;

	public int deleteReply(@Param("board_no") int baord_no, @Param("board_type") String board_type,
			@Param("reply_no") int reply_no) throws Exception;

}
