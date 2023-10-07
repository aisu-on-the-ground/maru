package com.lab.aisu.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lab.aisu.dao.MemberDAO;
import com.lab.aisu.dto.MemberDTO;
import com.lab.aisu.service.MemberService;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO dao;

	@Override
	public MemberDTO findMemberById(String memberId, String memberPw) {
		
		MemberDTO dto = dao.selectMemberById(memberId);
		log.info("* * * 로그인");
//		try {
//			password = dto.getMemberPw();
//		} catch (NullPointerException e) {
//			e.printStackTrace();
//			return null;
//		}

		return checkPassword(dto, memberPw) ? dto : null;
//		return password.equals(memberPw) ? dto : null;
	}

	public boolean checkPassword(MemberDTO dto, String memberPw) {
		String password = "";
		
		try {
			password = dto.getMemberPw();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		
		return password.equals(memberPw) ? true : false;
	}

}