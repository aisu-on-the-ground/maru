package com.lab.aisu.service;

import com.lab.aisu.dto.MemberDTO;

//	- 서비스 클래스 안에서 메서드 명을 작성 할 때는 아래와 같은 접두사를 붙인다.
//	findOrder() - 조회 유형의 service 메서드
//	addOrder() - 등록 유형의 service 메서드
//	modifyOrder() - 변경 유형의 service 메서드
//	removeOrder() - 삭제 유형의 service 메서드
//	saveOrder() – 등록/수정/삭제 가 동시에 일어나는 유형의 service 메서드

public interface MemberService {

	public MemberDTO findMemberById(String memberId, String memberPw);
}
