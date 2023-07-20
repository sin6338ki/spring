package com.smhrd.boot.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity //JPA가 클래스를 관리할 수 있도록 지정해주는 어노테이션
@Table(name="andmember2") //생성되는 테이블 이름(메핑할 테이블 이름)
@Data
public class AndMember {
	
	//jpa 사용시 @id 지정 필수
	//member 식별자(primary key) -> jpa에서 자동으로 생성
	//generatedValue : autoIncrement
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="m_idx")	//컬럼이름 지정
	private int mIdx; 
	
	@Column(name="id", length=50)
	private String id;
	@Column(name="pw", length=50)
	private String pw;
	@Column(name="tel", length=50)
	private String tel;
	@Column(name="birth", length=50)
	private String birth;
}
