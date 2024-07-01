package com.community.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.community.service.UserService;
import com.community.vo.UserVO;

@Controller
public class UserController {
	static Logger log = LoggerFactory.getLogger(UserController.class);
	private UserService userservice;

	@Autowired
	public UserController(UserService userservice) {
		this.userservice = userservice;
	}
	
	// 내 정보 수정 페이지
	@RequestMapping(value = { "/user/info" })
	public String getModifyUserInfoView() throws Exception {
		
		return "Board/getUserInfo";
	}
	
	// 로그인
	@RequestMapping(value = { "/user/login.do" }, method = RequestMethod.POST)
	@ResponseBody
	public int loginAccount(@RequestParam("user_id") String user_id, @RequestParam("user_pw") String user_pw) throws Exception {
		
		log.info("login in progress...");
		return userservice.login(user_id, user_pw);
		
	}
	
	// 계정 생성
	@RequestMapping(value = { "/user/createuser.do" }, method = RequestMethod.POST)
	@ResponseBody
	public int createAccount(@RequestBody UserVO userVO) throws Exception {
		
		log.info("createUser in progress...");
		return userservice.createUser(userVO);
		
	}
	
	// 계정 조회
	@RequestMapping(value = { "/user/readuser.do" }, method = RequestMethod.POST)
	@ResponseBody
	public UserVO readAccount() throws Exception {
		
		log.info("readUser in progress...");
		return userservice.readUser("id", "pwd");
		
	}
	
	// 계정 수정
	// 비밀번호 변경
	@RequestMapping(value = { "/user/updateuserpw.do" }, method = RequestMethod.POST)
	@ResponseBody
	public int updateUserPw(@RequestParam("user_pw") String user_pw) throws Exception {
		
		log.info("updateUser in progress...");
		return userservice.updateUserPw(user_pw);
		
	}
	
	// 별명 변경
	@RequestMapping(value = { "/user/updateuserchr.do" }, method = RequestMethod.POST)
	@ResponseBody
	public int updateUserChar(@RequestParam("user_chr") String user_chr) throws Exception {
		
		log.info("updateUser in progress...");
		return userservice.updateUserChr(user_chr);
		
	}
	
	// 이메일 변경
	@RequestMapping(value = { "/user/updateuseremail.do" }, method = RequestMethod.POST)
	@ResponseBody
	public int updateEmail(@RequestParam("user_email") String user_email) throws Exception {
		
		log.info("updateUser in progress...");
		return userservice.updateUserEmail(user_email);
		
	}
	
	// 이미지 변경
	@RequestMapping(value = { "/user/updateuserimage.do" }, method = RequestMethod.POST)
	@ResponseBody
	public int updateImage(@RequestParam("user_image") String user_image) throws Exception {
		
		log.info("updateUser in progress...");
		return userservice.updateUserImage(user_image);
		
	}
	
	// 계정 탈퇴
	@RequestMapping(value = { "/user/delete.do" }, method = RequestMethod.POST)
	@ResponseBody
	public int deleteAccount() throws Exception {
		
		log.info("deleteUser in progress...");
		return userservice.deleteUser("id", "pwd");
		
	}
}
