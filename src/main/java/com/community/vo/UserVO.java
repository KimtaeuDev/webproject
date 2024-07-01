package com.community.vo;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class UserVO {
	
	private int user_no;
	private String user_id;
	private String user_pw;
	private String user_chr;
	private String user_email;
	private String user_regdate;
	private String user_image;
	
}
