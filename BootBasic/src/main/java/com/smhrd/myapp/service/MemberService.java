package com.smhrd.myapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.smhrd.myapp.domain.Member;
import com.smhrd.myapp.mapper.MemberMapper;

@Service
public class MemberService {
	
	//외부에서 생성 후 주입 > 의존성 주입
	@Autowired
	private MemberMapper mapper;
	
	//service의 메서드를 controller에서 호출하여 사용
	//mapper 기능 호출
	public List<Member> memberList(){
		return mapper.memberList();
	}
}
