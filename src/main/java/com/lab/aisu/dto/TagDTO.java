package com.lab.aisu.dto;

import lombok.Data;

@Data
public class TagDTO {
	
	private int memberNo;
	private int tagNo;
	private String tagName = "";
	private char tagStatus = 'T';

}
