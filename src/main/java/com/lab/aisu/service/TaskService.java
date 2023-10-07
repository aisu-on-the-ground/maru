package com.lab.aisu.service;

import java.util.List;

import com.lab.aisu.dto.TaskDTO;

public interface TaskService {
	
	public List<TaskDTO> selectTaskList(int memberNo);

}
