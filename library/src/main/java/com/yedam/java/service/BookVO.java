package com.yedam.java.service;

import lombok.Data;

@Data
public class BookVO {
	private String bookName;
	private String name;
	private String content;
	private String rent;
	
	
	@Override
	public String toString() {
		
		return "책제목 : " + bookName
				+ ", 저자명 : " + name
				+ ", 내용 : + " + content
				+ ", 대여여부 : " + rent;
	}
	
	

}
