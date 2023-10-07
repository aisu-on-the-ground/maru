package com.lab.aisu.dao;

import java.util.ArrayList;
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
import com.lab.aisu.dto.TaskDTO;
import com.lab.aisu.util.TaskOrderChanger;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class, WebMvcContextConfiguration.class })
@WebAppConfiguration
@Log4j
public class TestTaskDAO {
	
	@Autowired
	private TaskDAO dao;
	
	@Test
	public void testInsert() {
		TaskDTO dto = new TaskDTO();
		int result;
		
		dto.setMemberNo(1);
		dto.setTaskName("테스트용 과제 C");
		
		dto.setTaskDesc("테스트테스트테스트테스트테스트테스트테스트테스트테스트");
		dto.setTaskStartTime("PM-03-00");
		dto.setTaskEndTime("PM-03-30");
		
		log.info("\n* * * 인서트 전 Ord: " + dto.getTaskOrder());
		log.info("\n* * * 인서트 전 taskNo: " + dto.getTaskNo());
		dao.insertTask(dto);
		dto.setTaskNo(dto.getTaskOrder());
		log.info("\n* * * 인서트 후 Ord: " + dto.getTaskOrder());
		log.info("\n* * * 인서트 후 taskNo: " + dto.getTaskNo());
		
		result = dao.updateTaskOrder(dto.getTaskNo(), dto.getTaskOrder());
		log.info("\n* * * result: " + result);
		
	}
	
	@Test
	public void testSelectList() {
		int memberNo = 1;
		
		List<TaskDTO> list = new ArrayList<TaskDTO>();
		list = dao.selectTaskList(memberNo);
		
		log.info("\n* * * dto list: " + list);
		
	}
	
	@Test
	public void testSelectOne() {
		int memberNo = 1;
		int taskNo = 3;
		
		TaskDTO dto = new TaskDTO();
		dto = dao.selectTask(memberNo, taskNo);
		
		log.info("\n* * * dto: " + dto);
		
	}
	
	@Test
	public void testUpdate() {
		TaskDTO dto = new TaskDTO();
		int result;
		
		dto.setTaskNo(5);
		
		dto.setTagNo(1);
		dto.setTaskName("5번 과제 수정");
		dto.setTaskDesc("수정수정");
		dto.setTaskStartTime(null);
		dto.setTaskEndTime("AM-01-30");
		
		result = dao.updateTask(dto);
		log.info("\n* * * result: " + result);
	}
	
	@Test
	public void testDelete() {
		int taskNo = 6;
		int result;
		
		result = dao.updateTaskStatus(taskNo);
		log.info("\n* * * result: " + result);
		
	}
	
	@Test
	public void testUpdateOrd() {
		TaskOrderChanger taskOrderChanger = new TaskOrderChanger();
		taskOrderChanger.setDirection("down");
		taskOrderChanger.setMemberNo(1);
		taskOrderChanger.setTaskOrder(4);
		
		List<TaskOrderChanger> list = new ArrayList<TaskOrderChanger>();
		list = dao.selectTaskOrderBoth(taskOrderChanger);
		
		TaskOrderChanger origin = list.get(0);
		TaskOrderChanger target = list.get(1);
		
		dao.updateTaskOrder(origin.getTaskNo(), target.getTaskOrder());
		dao.updateTaskOrder(target.getTaskNo(), origin.getTaskOrder());
		
	}
	
	@Test
	public void testUpdateOrdMap() {
		// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
		// * * * Map 방식을 포기한 이유를 꼭 기억하고 잘 정리해서 면접 때 말하자... * * * 
		// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * 
		
		// 1. 쿼리에 파라미터로 보내야 하는 값이 문자열과 정수. 즉, 2가지 타입이었음
		// 2. 2가지 타입의 파라미터를 한번에 보내려면 map을 쓰거나 class를 하나 만들어야 했음
		// 3. map을 사용하면 value를 Object 타입으로 받아 어떤 타입의 값이든 파라미터로 보낼 수 있기 때문
		// 4. 보내는 건 문제가 없는데 다시 정수 값 2개를 받아올 때 문제가 생김
		// 5. map에서 받아온 정수 데이터는 .get()으로 꺼내니 원시타입 long으로 자동 형변환이 되어 꺼내졌음
		// 6. 원시타입인 long형은 int형으로 바로 형변환 할 수 없고, 다른 타입을 거쳐야 했음
		// 7. 대표적인게 long->String-int 하거나 long->Long(wrapper class)-> .intValue()로 int형 변환하는 게 있음
		// 8. 비즈니스로직이 너무 지저분해짐. 개인프로젝트긴 했지만 만약 협업이었다면 가시성이 떨어져 나 이외의 사람이 유지보수하기 어려웠을 것임
		// 9. 결국 클래스를 따로 만들어서 문자열 변수와 정수형 변수를 각각 만들어놓고 파라미터로 객체를 보내는 방법을 택함
		
		String direction = "down";
		int memberNo = 1;
		int taskOrder = 8;
		int taskNo;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("direction", direction);
		map.put("memberNo", memberNo);
		map.put("taskOrder", taskOrder);
		
		List<Map<String, Object>> list = dao.selectTaskOrderBothMap(map);
		
		dao.updateTaskOrder(((Long)list.get(0).get("task_no")).intValue(), ((Long)list.get(1).get("task_order")).intValue());
		dao.updateTaskOrder(((Long)list.get(1).get("task_no")).intValue(), ((Long)list.get(0).get("task_order")).intValue());
		
	}

}
