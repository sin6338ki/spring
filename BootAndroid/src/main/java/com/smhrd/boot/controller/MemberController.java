package com.smhrd.boot.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.smhrd.boot.model.AndMember;
import com.smhrd.boot.service.MemberService;

//spring에서 사용하는 class => 어노테이션 @을 붙여야함
@RestController
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	//localhost:8089/join으로 요청이 들어왔을 때
	@PostMapping("/join")
	public String join(HttpServletRequest request) throws JsonMappingException, JsonProcessingException {
		// 키값이 일치하지 않을 때 런타임 오류 발생 => throws 처리
		
		//데이터가 json형태의 문자열로 들어옴 => request.getParameter("name") : String 타입 
		//String => jsonObject로 형변환
		//필요한 자바 객체가 있을 때 => () 안에 정의
		String andMember = request.getParameter("AndMember");
		//					안드로이드에서 보내주는 키값		
		
		ObjectMapper om = new ObjectMapper(); //jackson-databind에서 제공 
		AndMember object = om.readValue(andMember, AndMember.class); //자바 객체 형태로 변환
		
		//서비스로 요청 전달	
		AndMember result = service.join(object);
		
		if(result!=null){
			return "success";
		}else {
			return "Fail";
		}		
	}
	
	@PostMapping("/login")
	public String login(HttpServletRequest request) {
		return null;
	}
}
