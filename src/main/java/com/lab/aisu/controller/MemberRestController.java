package com.lab.aisu.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.lab.aisu.dto.MemberDTO;
import com.lab.aisu.service.MemberService;

import lombok.extern.log4j.Log4j;

//	- 컨트롤러 클래스 안에서 메서드 명을 작성 할 때는 아래와 같은 접미사를 붙인다.
//	orderList() – 목록 조회 유형의 서비스
//	orderDetails() – 단 건 상세 조회 유형의 controller 메서드
//	orderSave() – 등록/수정/삭제 가 동시에 일어나는 유형의 controller 메서드
//	orderAdd() – 등록만 하는 유형의 controller 메서드
//	orderModify() – 수정만 하는 유형의 controller 메서드
//	orderRemove() – 삭제만 하는 유형의 controller 메서드

@Log4j
@RestController
@RequestMapping("/member/rest/*")
public class MemberRestController {

	@Autowired
	private MemberService service;
	
//	@GetMapping("/login")
//	public String loging(String memberId, String memberPw) {
//		String result = "fail";
//		log.info("* * * * memberId: " + memberId);
//		log.info("* * * * memberPw: " + memberPw);
//		
//		if(memberId == "aisu" && memberPw == "aisu") {
//			result = "success";
//		}
//		return result;
//	}
	
	
	@PostMapping(value = "/login", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public String login(@RequestBody String data, HttpSession session) {
		JsonParser parser = new JsonParser();
		JsonElement element = parser.parse(data);

		String memberId = element.getAsJsonObject().get("memberId").getAsString();
		String memberPw = element.getAsJsonObject().get("memberPw").getAsString();
		
		MemberDTO dto = service.findMemberById(memberId, memberPw);
		
		if(null != dto) {
			session.setAttribute("member", dto);
			session.setAttribute("memberNo", dto.getMemberNo());
		}
		
		return (null != dto) ? "true" : null;
	}
	
	@PostMapping("/join")
//	public String registerMember(@RequestBody MemberDTO memberDto) {
	public String registerMember(MemberDTO memberDto) {
		log.info("memberDTO = " + memberDto.getMemberId());
		log.info("memberDTO = " + memberDto.getMemberPw());
		log.info("memberDTO = " + memberDto.getMemberName());
		log.info("memberDTO = " + memberDto.getMemberEmail());
		return "success";
	}
	
	@GetMapping("/{memberNo}")
	public String getMemberDetail(@PathVariable int memberNo) {
		log.info("memberNo: " + memberNo);
		return "";
	}
	
	@PutMapping("/{memberNo}")
	public String modifyMember(@PathVariable int memberNo) {
		return "";
	}
	
	@DeleteMapping("/{memberNo}")
	public String removeMember(@PathVariable int memberNo) {
		return "success";
	}
	
//	1. 회원등록
//	registerMember
//	2. 회원정보 가져오기
//	getMemberDetail
//	3. 회원정보 수정해서 저장
//	modifyMember
//	4. 회원정보 삭제처리 (실제로 상태를 수정)
//	removeMember

}
