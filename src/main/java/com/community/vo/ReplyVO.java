package com.community.vo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class ReplyVO {
	
	private int board_no;
	private String board_type;
	private int reply_no;
	private int reply_group;
	private int reply_dept;
	private String reply_writer;
	private String reply_content;
	private String reply_regdate;
	private int reply_good;
	
}
