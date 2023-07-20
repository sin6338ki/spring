package com.smhrd.myapp.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.smhrd.myapp.domain.Member;

@Mapper
public interface MemberMapper {
	
	//실제 SQL 추가 : @annotation 추가 혹은 XML 추가
	public List<Member> memberList();
}
