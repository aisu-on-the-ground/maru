package com.lab.aisu.dto;

import lombok.Data;

@Data
public class MemberDTO {
	
	private int memberNo;
	private String memberId;
	private String memberPw;
	private String memberName;
	private String memberEmail;
	private char memberStatus;

}
