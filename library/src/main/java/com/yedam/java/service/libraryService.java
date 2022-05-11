package com.yedam.java.service;

import java.util.List;

public interface libraryService {
	// 1.전체조회
	List<BookVO> selectAllList();
	// 2.단건조회
	BookVO selectBookInfo(String name); // 이름 하나만 넘기겠다
	// 3.내용조회
	List<BookVO> selectBookList(String content);  // 단정지을 수 없을땐 리스트로 만드는게 좋다
	// 4.대여가능조회
	List<BookVO> selectRentalBookList();
	// 5.책 대여
	void rentalBook(String name); // 어떤 책을 반납할지 이름값만 받음
	// 6.책 반납
	void returnBook(String name);
	// 7.책 등록
	void insertBook(BookVO book);
}
