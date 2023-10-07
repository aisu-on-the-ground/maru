package com.lab.aisu.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.lab.aisu.config.ApplicationConfig;
import com.lab.aisu.config.WebMvcContextConfiguration;
import com.lab.aisu.dto.MemberDTO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class, WebMvcContextConfiguration.class})
@WebAppConfiguration
@Log4j
public class TestMemberDAO {
	
	@Autowired
	private MemberDAO dao;

	@Test
	public void testInsert() {
		MemberDTO dto = new MemberDTO();
		int result;
		
		dto.setMemberId("세번째");
		dto.setMemberPw("1234!@#$");
		dto.setMemberName("박테스트");
		dto.setMemberEmail("test3@test.net");
		
		result = dao.insertMember(dto);
		
		log.info("* * * dto: " + dto);
		log.info("* * * result: " + result);
	}
	
	@Test
	public void testSelectOne() {
		int memberNo = 3;
		
		MemberDTO dto = dao.selectMember(memberNo);
		log.info("* * * dto: " + dto);
	}
	
	@Test
	public void testSelectOneById() {
		String memberId = "수정";
		
		MemberDTO dto = dao.selectMemberById(memberId);
		log.info("* * * dto: " + dto);
	}
	
	@Test
	public void testUpdateMember() {
		MemberDTO dto = new MemberDTO();
		int result;
		
		dto.setMemberNo(2);
		dto.setMemberId("수정");
		dto.setMemberPw("수정");
		dto.setMemberName("수정");
		dto.setMemberEmail("수정");
		
		result = dao.updateMember(dto);
		log.info("* * * result: " + result);
	}
	
	@Test
	public void testDeleteMember() {
		int memberNo = 3;
		int result;
		
		result = dao.updateMemberStatus(memberNo);
		log.info("* * * result: " + result);
	}
}
