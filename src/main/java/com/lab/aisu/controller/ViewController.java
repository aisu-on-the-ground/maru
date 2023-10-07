package com.lab.aisu.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.log4j.Log4j;
@Log4j
@Controller
public class ViewController {

	@GetMapping("/menu")
	public String getTask(Model model, HttpSession session) {
		log.info("* * * 투데이");
		log.info("* * * session.member: " + session.getAttribute("member"));
		
		return "menu";
	}
	
	@GetMapping("/login")
	public String login() {
		log.info("* * * 로그인 페이지");
		return "login";
	}
	
	@GetMapping("/logout")
	public String login(HttpSession session) {
		session.invalidate();
		log.info("* * * 로그아웃");
		return "redirect:login";
	}

}
