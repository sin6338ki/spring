package com.smhrd.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//View 반환 컨트롤러 지정
@Controller
public class HomeController {

	//스프링 3버전에서 사용했던 방식
//	@RequestMapping(value="/", method=RequestMethod.GET)
	@GetMapping(value="/")
	//Get방식 매핑, 포스트는 PostMapping
	public String main() {
		return "main";
	}
	
}
