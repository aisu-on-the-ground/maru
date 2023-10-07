package com.lab.aisu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lab.aisu.service.MemberService;
import com.lab.aisu.service.PointService;

@RestController
@RequestMapping("/point/rest/*")
public class PointRestController {
	
	@Autowired
	private PointService service;
	
}
