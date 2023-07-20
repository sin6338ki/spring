package com.smhrd.myapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication : 어노테이션이 붙은 클래스의 위치가 굉장히 중요!
//클래스의 위치를 기준으로 하위에 있는 설정 내용들을 읽어주기 때문
//클래스의 위치가 프로젝트의 최 상단에 위치해야 함!
//스프링 부트의 설정 자동화, bean(객체) 읽어오거나 생성하는 작업을 자동으로 설정해줌
@SpringBootApplication
public class BootBasicApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootBasicApplication.class, args);
	}

}
