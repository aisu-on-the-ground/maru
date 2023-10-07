package com.lab.aisu.dto;

import lombok.Data;

@Data
public class PointDTO {

	private int memberNo;
	private int pointOrder;
	private String pointYmd;
	private String pointInfo;
	private char pointType;
	private int pointAmount;
	private int pointBalance;
	private int pointGrade;
	
}
