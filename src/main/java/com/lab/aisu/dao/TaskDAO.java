package com.lab.aisu.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lab.aisu.dto.TaskDTO;
import com.lab.aisu.util.TaskOrderChanger;

@Mapper
public interface TaskDAO {
	
	public int insertTask(TaskDTO dto);
	
	public int updateTaskOrder(@Param("taskNo") int taskNo, @Param("taskOrder") int taskOrder);
	
	public TaskDTO selectTask(@Param("memberNo") int memberNo, @Param("taskNo") int taskNo);
	
	public List<TaskDTO> selectTaskList(int memberNo);

	public int updateTask(TaskDTO dto);
	
	public int updateTaskStatus(int taskNo);

	public List<TaskOrderChanger> selectTaskOrderBoth(TaskOrderChanger taskOrderChanger);
	
	public List<Map<String, Object>> selectTaskOrderBothMap(Map<String, Object> map);
}
