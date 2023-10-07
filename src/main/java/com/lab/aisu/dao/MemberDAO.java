package com.lab.aisu.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lab.aisu.dto.MemberDTO;

//	- Mapper 클래스 안에서 메서드 명을 작성 할 때는 아래와 같은 접두사를 붙인다.
//	selectOrder(); - 조회 유형의 mapper 메서드
//	insertOrder(); - 등록 유형의 mapper 메서드
//	updateOrder(); – 변경 유형의 mapper 메서드
//	deleteOrder(); - 삭제 유형의 mapper 메서드

@Mapper
public interface MemberDAO {

	public int insertMember(MemberDTO dto);

	public MemberDTO selectMember(@Param("memberNo") int memberNo);
	
	public MemberDTO selectMemberById(@Param("memberId") String memberId);

	public int updateMember(MemberDTO dto);

	public int updateMemberStatus(@Param("memberNo") int memberNo);

}
