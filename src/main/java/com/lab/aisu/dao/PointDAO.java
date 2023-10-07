package com.lab.aisu.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.lab.aisu.dto.PointDTO;

@Mapper
public interface PointDAO {
	
	// 1. 계정 생성시 회원번호와 연결된 포인트 레코드 생성
	public int insertPointNewMember(int memberNo);
	
	// 2. 내 누적포인트, 등급 가져오기 (가장 최근) select
	public PointDTO selectPoint(int memberNo);
	
	// 3. 내 오늘획득포인트 가져오기 select
	public int selectPointOfToday(int memberNo);
	
	// 4. 포인트 획득(증가) insert
	// 5. 포인트 사용(감소) insert
	public int insertPoint(PointDTO dto);
	
	// 6. 등급 상승 (포인트 감소, 등급 증가) insert
	public int insertGradeUp(@Param("memberNo") int memberNo, @Param("pointAmount") int pointAmount);

}
