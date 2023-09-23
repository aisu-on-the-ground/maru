package com.lab.aisu.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lab.aisu.service.RoleService;

@Controller
public class PlusController {

	@Autowired
	private RoleService roleService;

	@GetMapping(path = "/roletest")
	public String roletest(@RequestParam(name = "roleId") int roleId, @RequestParam(name = "description") String description) {
		roleService.roletest(roleId, description);
		System.out.println("roleId: " + roleId + " description: " + description);
		return "plusForm";
	}

	@GetMapping(path = "/plusform")
	public String plusform() {
		return "plusForm";
	}

	@PostMapping(path = "/plus")
	public String plus(@RequestParam(name = "value1", required = true) int value1,
			@RequestParam(name = "value2", required = true) int value2, ModelMap modelMap) {
		int result = value1 + value2;

		modelMap.addAttribute("value1", value1);
		modelMap.addAttribute("value2", value2);
		modelMap.addAttribute("result", result);
		return "plusResult";
	}

	@PostMapping(path = "/plus2")
	public String plus2(int value3, int value4, ModelMap modelMap) {
		int result = value3 + value4;

		modelMap.addAttribute("value1", value3);
		modelMap.addAttribute("value2", value4);
		modelMap.addAttribute("result", result);
		return "plusResult";
	}

}
