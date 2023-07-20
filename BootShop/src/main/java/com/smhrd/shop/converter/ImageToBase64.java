package com.smhrd.shop.converter;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import org.apache.commons.io.FileUtils;

public class ImageToBase64 extends imageConverter<File, String> {

	@Override
	public String convert(File f) throws IOException {
		
		//파일을 문자열로 변환하는 코드
		//1. 파일 가지고 와서 바이트 배열 형태로 읽기
		byte[] fileContent = FileUtils.readFileToByteArray(f);
		
		//2. 바이트배열 형태를 인코딩하여 문자열로 변경(Base64)
		String result = Base64.getEncoder().encodeToString(fileContent);
		
		return result;
	}
}
