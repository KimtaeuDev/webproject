package com.community.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.community.vo.FileVO;

@Mapper
public interface FileMapper {

	public int registImage(@Param("board_no") int board_no, @Param("board_type") String board_type,
			@Param("imagelist") List<Map<String, String>> imagelist) throws Exception;

	public List<FileVO> getFileList(@Param("board_type") String board_type, @Param("board_no") int board_no)
			throws Exception;

}
