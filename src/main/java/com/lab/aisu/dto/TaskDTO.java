package com.lab.aisu.dto;

import lombok.Data;

@Data
public class TaskDTO {

	private int memberNo;
	private int taskNo;
	private int taskOrder;
	private int tagNo;
	private String taskName;
	private String taskDesc;
	private String taskStartTime;
	private String taskEndTime;
	private String taskRegYmd;
	private String taskFinYmd;
	private char taskStatus;
	
}
