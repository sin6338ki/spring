package com.smhrd.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import com.smhrd.board.converter.ImageToBase64;
import com.smhrd.board.converter.ImageConverter;
import com.smhrd.board.domain.Board;
import com.smhrd.board.service.BoardService;

//jsp view 반환하는 컨트롤러
@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@GetMapping("/board")
	public String boarList(Model model) {
		List<Board> boardList = service.boardList();
		model.addAttribute("list", boardList);
		return "boardlist";
	}
	
	@GetMapping("/board/writeform")
	public String writeForm() {
		return "boardfoam";
	}
	
	//@RequestPart : multipart/form-data에 특화된 어노테이션
	@PostMapping("/board/write")
	public String write(Board b, @RequestPart("photo") MultipartFile file) {
		System.out.println(b.getTitle()+","+b.getContent()+","+b.getWriter());
		System.out.println(file.getOriginalFilename());
		
		String newFileName = UUID.randomUUID().toString() + file.getOriginalFilename() ;
		//넘어오는 이미지 파일은 저장(지정된 경로에)
		//board table(DB)에 '랜덤숫자 + 파일 이름'만 저장
		//확장자는 file에 저장되어 있으므로 file이름을 뒤에 오게끔 저장해야함
		
		 try {
			file.transferTo(new File(newFileName));  //저장 기능
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		 b.setImg(newFileName);
		 //b(Board) >> title. content. writer. img(fileName) 가지고 있음
		 //board table(DB) >> 랜덤숫자 + 파일 이름만 저장
		 int cnt = service.write(b);
		 
		 System.out.println(cnt);
		 
		 if(cnt>0) {
			 //boardlist.jsp 반환
			 return "redirect:/board";
		 }else {
			 //boardform.jsp 반환
			 return "redirect:/board/writeform";
		 }
	}
	
	@GetMapping("/board/content/{idx}")
	public String content(@PathVariable("idx") int idx, Model model) {
		Board b = service.content(idx);
		
		//이미지 가져오기
		File file = new File("c:\\uploadImage\\" + b.getImg());
		
		ImageConverter<File, String> converter = new ImageToBase64();
		
		try {
			String fileStringValue = converter.convert(file);
			System.out.println(fileStringValue);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("board", b);
		
		return "boardcontent";
	}
}
