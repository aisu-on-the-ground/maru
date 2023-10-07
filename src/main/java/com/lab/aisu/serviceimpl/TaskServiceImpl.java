package com.lab.aisu.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab.aisu.dao.TaskDAO;
import com.lab.aisu.dto.TaskDTO;
import com.lab.aisu.service.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

	@Autowired
	private TaskDAO dao;

	@Override
	public List<TaskDTO> selectTaskList(int memberNo) {
		List<TaskDTO> list = dao.selectTaskList(memberNo);
		
		return list;
	}

}
