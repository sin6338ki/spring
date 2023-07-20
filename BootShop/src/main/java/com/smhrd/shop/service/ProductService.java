package com.smhrd.shop.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.smhrd.shop.converter.ImageToBase64;
import com.smhrd.shop.converter.imageConverter;
import com.smhrd.shop.domain.Product;
import com.smhrd.shop.mapper.ProductMapper;

@Service
public class ProductService {
	
	@Autowired
	private ProductMapper mapper;
	
	@Autowired
	private ResourceLoader resourceLoader; //특정 경로에 있는 파일 가져오기
	
	//product 전체 정보 불러오기
	public JSONArray productList() {
		List<Product> list = mapper.productList();
		
		//현재 list안의 img는 파일명만 가지고 있음(실제파일X). 
		//실제 프론트에 넘겨줄 때는 이미지 자체를 넘겨주어야 함!
		//부트 서버에 이미지 저장 >> 이미지 자체를 리액트로 넘길 예정 
		//Product에 있는 이미지를 통해서 실제 파일 이름을 가지고 실제 파일을 가지고 와야함 (static/img/...)
		//스프링에서 리액트로 파일을 응답해줄 때 파일의 형태가 굉장히 중요함!
		//파일 자체를 넘길 수는 없음
		//어떻게 보내야 하냐, 컴퓨터가 알아들을 수 있는 문자(바이트) 형태로 변경해야 함
		//이미지 자체는 문자로 바꾸는 것!
		//최종적으로는 Product의 img 필드 값(파일 이름)을 이미지를 byte로 변환된 문자열로 바꾼걸로 수정해야함
		
		//JsonArray에 JsonObject가 들어있는 형식으로 응답
		//List >> JsonArray
		JSONArray jsonArray = new JSONArray();
		//List안의 Product >> JsonObject
		imageConverter<File,String> converter = new ImageToBase64();
		for(Product p : list) {
			//1. img 필드값 수정 (파일이름 >> 이미지 바이트 문자열 형태로 변환)
			//1-1. 변환할 파일 실제 경로 정의
			String filePath = "classpath:/static/img/" + p.getImg();
			Resource resource = resourceLoader.getResource(filePath); //파일에 대한 메타 데이터 반환
			String fileStringValue = null;
			try {
				fileStringValue = converter.convert(resource.getFile());
			}catch(IOException e) {
				e.printStackTrace();
			}
			
			//가지고 온 product img를 변환된 바이트 문자열로 변경
			p.setImg(fileStringValue);
			
			//2. Product 객체를 JSONObject 형태(key:value)로 변환
			JSONObject obj = new JSONObject(); //비어있는 jsonObject 생성
			obj.put("product", p); //jsonObject에 데이터 베이스에 저장된 product 객체 저장
			jsonArray.add(obj); //JsonArray에 JsonObject 추가
		}
		return jsonArray;
	}
	
	public JSONObject productOne(String pcode) {
		Product product = mapper.productOne(pcode);
		
		imageConverter<File,String> converter = new ImageToBase64();
		
		String filePath = "classpath:/static/img/" + product.getImg();
		Resource resource = resourceLoader.getResource(filePath);
		String fileStringValue = null;
		try {
			fileStringValue = converter.convert(resource.getFile());
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		product.setImg(fileStringValue);
		
		JSONObject obj = new JSONObject();

		obj.put("product", product);
		
		return obj;
	}
}
