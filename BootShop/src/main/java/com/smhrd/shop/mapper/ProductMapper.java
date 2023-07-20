package com.smhrd.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONObject;

import com.smhrd.shop.domain.Product;

@Mapper
public interface ProductMapper {

	//Product 전체 정보 불러오기
	public List<Product> productList();
	
	//Product 한 개 정보 불러오기
	public Product productOne(String pcode);
	
}
