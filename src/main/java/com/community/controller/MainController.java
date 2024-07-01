package com.community.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class MainController {
	
	private static Logger log = LoggerFactory.getLogger(BoardController.class);
	
	/* 페이지 요청 */
	// 메인 페이지
	@GetMapping("/")
	public String main() throws Exception {

		log.info("Linked to MainPage");
		return "MainPage";

	}
}
