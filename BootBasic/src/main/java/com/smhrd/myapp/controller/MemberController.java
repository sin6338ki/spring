package com.smhrd.myapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd.myapp.domain.Member;
import com.smhrd.myapp.service.MemberService;

//데이터(모델)을 반환하는 컨트롤러 @RestController : @Controller + @ResponseBody 합쳐진 형태
//일반 컨트롤러는 view를 반환
@RestController
public class MemberController {
	
	@Autowired
	private MemberService service;
	//Spring 기본 순서
	//(요청 처리) Controller > Service > mapper Interface > mapper XML
	//(결과 처리) mapperXML > mapper Interface > Service > Controller 
	// Controller : 요청 받는거, 응답 하는거에 집중
	// Service : 데이터 가공, 요청 가공에 집중
	@GetMapping(value="/member")
	public List<Member> memberList() {
		//List<Member> 반환
		// >> 자바에서 사용하는 형태로 이 형태 그대로는 javaScript에서 사용할 수 없음. 
		// >> 그러나 바로 사용할 수 있었음 ----> 왜냐? jackson library 때문
		//Jackson library
		// : java object를 json으로 변환
		// : json을 javaObject로 변환해주는 기능을 가지고 있음
		// 일반 스프링 프레임워크에서 사용하려면 디펜던시에 추가해야 함 
		// 
		return service.memberList();
	}

}
