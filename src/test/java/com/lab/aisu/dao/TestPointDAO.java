package com.lab.aisu.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.lab.aisu.config.ApplicationConfig;
import com.lab.aisu.config.WebMvcContextConfiguration;
import com.lab.aisu.dto.PointDTO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class, WebMvcContextConfiguration.class })
@WebAppConfiguration
@Log4j
public class TestPointDAO {

	@Autowired
	private PointDAO dao;

	@Test
	public void testInsertPointNewMember() {
		int memberNo = 1;
		
		dao.insertPointNewMember(memberNo);
	}

	@Test
	public void testSelectPoint() {
		int memberNo = 1;

		PointDTO dto = new PointDTO();
		dto = dao.selectPoint(memberNo);
		
		log.info("* * * dto: " + dto);
	}
	
	@Test
	public void testSelectPointOfToday() {
		int memberNo = 1;
		int pointOfTodayAdded; 
		pointOfTodayAdded = dao.selectPointOfToday(memberNo);
		
		log.info("* * * pointOfTodayAdded: " + pointOfTodayAdded);
	}

	@Test
	public void testInsertPoint() {
		int memberNo = 1;
		
		PointDTO dto = new PointDTO();
		dto.setMemberNo(1);
		dto.setPointType('-');
		dto.setPointAmount(50);
		
		dao.insertPoint(dto);
	}
	
	@Test
	public void testInsertGradeUp() {
		int memberNo = 1;
		int pointAmount = 10;
		
		dao.insertGradeUp(memberNo, pointAmount);
	}
	
	
	
	
	
	
	@Test
	public void testSelectList() {
		int memberNo = 1;

//		List<TagDTO> list = dao.selectTagList(memberNo);
//		log.info("* * * list: " + list);
	}

	@Test
	public void testUpdate() {
		boolean result = false;

//		result = (dao.updateTag(dto) == 1);
//		log.info("* * * result: " + result);
	}

	@Test
	public void testDelete() {
		boolean result = false;
		
//		dto.setTagStatus(tagStatus);
		
//		result = (dao.updateTag(dto) == 1);
//		log.info("* * * result: " + result);
	}

}
