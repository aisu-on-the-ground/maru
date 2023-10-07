package com.lab.aisu.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.lab.aisu.config.ApplicationConfig;
import com.lab.aisu.config.WebMvcContextConfiguration;
import com.lab.aisu.dto.MemberDTO;
import com.lab.aisu.dto.TagDTO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class, WebMvcContextConfiguration.class })
@WebAppConfiguration
@Log4j
public class TestTagDAO {

	@Autowired
	private TagDAO dao;

	@Test
	public void testInsert() {
		TagDTO dto = new TagDTO();
		int result;

		dto.setMemberNo(2);
		dto.setTagName("뉴타입");

		result = dao.insertTag(dto);

		log.info("* * * tagNo: " + dto.getTagNo());
		log.info("* * * result: " + result);
	}

	@Test
	public void testSelectSameTagName() {
		String tagName = "수학  ";
		boolean result = false;
		String message;
		
		TagDTO dto = new TagDTO();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberNo", 1);
		map.put("tagName", tagName);
		dto = dao.selectSameTagName(map);

		// 1. 중복된 것이 있고 status가 F일 때 => F를 T로 변경.
		// 2. 중복된 것이 있고 status가 T일 때 => 같은 이름의 태그가 이미 있습니다.
		if(dto != null) { // 같은 이름의 태그가 존재
			switch(dto.getTagStatus()) {
			case 'T': // 태그가 활성화 중일 때
				message = "이미 같은 이름의 태그가 존재합니다.";
				log.info("* * * message: " + message);
				log.info("* * * result: " + result);
				break;
			case 'F': // 태그가 비활성화 중일 때
				dto.setTagStatus('T');
				result = (dao.updateTag(dto) == 1);
				log.info("* * * result: " + result);
				break;
			}
		}
		
		log.info("* * * dto: " + dto);
	}

	@Test
	public void testSelectOne() {
		int tagNo = 3;

		TagDTO dto = dao.selectTag(tagNo);
		log.info("* * * dto: " + dto);
	}

	@Test
	public void testSelectList() {
		int memberNo = 1;

		List<TagDTO> list = dao.selectTagList(memberNo);
		log.info("* * * list: " + list);
	}

	@Test
	public void testUpdate() {
		boolean result = false;
		
		TagDTO dto = new TagDTO();
		dto.setTagNo(4);
		dto.setTagName("취미");

		result = (dao.updateTag(dto) == 1);
		log.info("* * * result: " + result);
	}

	@Test
	public void testDelete() {
		boolean result = false;
		
		TagDTO dto = new TagDTO();
		dto.setTagStatus('F');
		
		char tagStatus;
		
		// 현재 Status와 다른 값으로 변경하는 메소드로 사용 (사용자/관리자 시점에서 하나의 메소드 사용을 위해)
		// 삼항연산자를 이용해서 updateTag()를 재사용하는 것으로 변경하자
		tagStatus = (dto.getTagStatus() == 'T') ? 'F' : 'T';
		dto.setTagStatus(tagStatus);
		
		result = (dao.updateTag(dto) == 1);
		log.info("* * * result: " + result);
	}

}
