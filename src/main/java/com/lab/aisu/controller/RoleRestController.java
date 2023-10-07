package com.lab.aisu.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lab.aisu.service.RoleService;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
public class RoleRestController {
	
	@Autowired
	private RoleService service;
	
	@RequestMapping(path="/getrole")
	public String getRole(@RequestParam(name = "roleId") int roleId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map = service.getRole(roleId);
		log.info("roleId = " + map.get("role_id"));
		log.info("description = " + map.get("description"));
		return "success";
	}
	
	@GetMapping(path = "/roletest")
	public String roletest(@RequestParam(name = "roleId") int roleId, @RequestParam(name = "description") String description) {
		service.roletest(roleId, description);
		System.out.println("roleId: " + roleId + " description: " + description);
		return "plusForm";
	}

}
