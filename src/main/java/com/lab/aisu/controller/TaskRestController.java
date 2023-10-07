package com.lab.aisu.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.lab.aisu.dto.TaskDTO;
import com.lab.aisu.service.TaskService;

import lombok.extern.log4j.Log4j;

@Log4j
@RestController
@RequestMapping("/task/rest/*")
public class TaskRestController {
	
	@Autowired
	private TaskService service;
	
	
//	@SessionAttribute("memberNo")
	
	@GetMapping(value = "/taskList", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public List<TaskDTO> todayList(@SessionAttribute("memberNo") int memberNo) {
		log.info("* * * memberNo: " + memberNo);
		
		List<TaskDTO> list = service.selectTaskList(memberNo);
		log.info(list);
		
		return list;
	}
	
	
}

