package com.community.vo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class BoardVO {
	
	private int board_no;
	private String board_title;
	private String board_writer;
	private String board_content;
	private String board_type;
	private int board_views;
	private int board_good;
	private int board_bad;
	private String board_regdate;
	private String board_updatedate;
	private int reply_count;
}

