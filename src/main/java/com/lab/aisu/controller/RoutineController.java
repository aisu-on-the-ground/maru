package com.lab.aisu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RoutineController {
	
	@RequestMapping("/hello")
	public String hello() {
		return "hello";
	}

}
