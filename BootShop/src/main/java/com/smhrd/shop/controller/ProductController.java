package com.smhrd.shop.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd.shop.service.ProductService;

@RestController
//CORS : Cross-Origin
@CrossOrigin("http://localhost:3000")
public class ProductController {
	//1. 모든 상품 정보 불러오기
	//2. 특정 상품 1개에 대한 정보 불러오기
	//뷰를 반환하지 않고 모델만 반환할 것! (뷰는 react로 보여줄 예정) >> RestController
	
	@Autowired
	private ProductService service;
	
	//1. 모든 상품 정보 불러오기
	@GetMapping("/")
	public JSONArray productList() {
//		System.out.println("이곳은 8090");
		JSONArray array = service.productList();
		return array;
	}
	
	//2. 한개 상품 정보 불러오기
	@GetMapping("/{pcode}")
	public JSONObject productOne(@PathVariable("pcode") String pcode) {
		JSONObject obj = service.productOne(pcode);
		return obj;
	}
}
