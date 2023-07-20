package com.smhrd.shop.converter;

import java.io.IOException;

//F : 파일
//S : 파일을 어떤 형태로 변환할 지 지정 (바이트 형태의 문자열 : String)
public abstract class imageConverter<F, S> {
	//<> : Generic, 사용자가 필요할 때 형태를 지정할 수 있음
	
	//실제 변환할 때 오버라이딩 할 추상 메서드 정의
	public abstract S convert(F f) throws IOException;
}
