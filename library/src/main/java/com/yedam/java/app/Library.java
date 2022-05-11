package com.yedam.java.app;

import java.util.List;
import java.util.Scanner;

import com.yedam.java.service.BookVO;

import library.com.yedam.dao.DataSource;

public class Library {
	
	private Scanner scanner = new Scanner(System.in);
	private DataSource dao = DataSource.getInstance();
	
	public Library() {
		while (true) {
			menuPrint();
			int menuNo = menuSelect();
			if (menuNo == 1) {
				selectBookList();
			} else if (menuNo == 2) {
				selectBookInfo();
			} else if (menuNo == 3) {
				selectSearchBookList();
			} else if (menuNo == 4) {
				selectRentalBookList();
			} else if (menuNo == 5) {
				rentalBook();
			} else if (menuNo == 6) {
				returnBook();
			} else if(menuNo == 7) {
				insertBookInfo();
			} else if (menuNo == 9) {
				end();
				break;
			}

		}
	}

	
	private void menuPrint() {
		System.out.println("============================================================================");
		System.out.println("1.전체조회 | 2.단건조회 | 3.내용검색 | 4.대여가능 | 5.책 대여 | 6.책 반납 | 7.책 등록 | 9.종료");
		System.out.println("============================================================================");
		System.out.print("메뉴선택>");
	}

	private int menuSelect() {
		int menuNo = 0;
		try {
			menuNo = Integer.parseInt(scanner.nextLine());
		} catch (Exception e) {
			System.out.println("없는 메뉴입니다.");
		}
		return menuNo;
	}

	private void selectBookList() {
		List<BookVO> list = dao.selectAllList();
		for (BookVO book : list) {
			System.out.println(book);
		}
	}
	
	private void selectBookInfo() {
		String name = inputBookName();
		BookVO book = dao.selectBook(name);
		if(book == null) {
			System.out.println("해당 책은 등록되어 있지 않습니다.");
		}else {
			System.out.println(book);
		}
	}
	
	private void selectSearchBookList() {
		String keyword = inputBookKeyword();
		List<BookVO> list = dao.selectAllList();
		for(BookVO book : list) {
			if(book.getBookContent().indexOf(keyword) != -1) {
				System.out.println(book);
			}
		}		
	}
	
	private void selectRentalBookList() {
		List<BookVO> list = dao.selecRentalBookList();
		for(BookVO book : list) {
			System.out.println(book);
		}
	}
	
	private void rentalBook() {
		String name = inputBookName();
		BookVO book = dao.selectBook(name);
		if(book == null) {
			System.out.println("해당 책은 등록되어 있지 않습니다.");
		}else if(book.getRental() == 1) {
			System.out.println("해당 책은 이미 대여중입니다.");
		}else {
			dao.rentalBook(name);
		}	
	}
	
	private void returnBook() {
		String name = inputBookName();
		BookVO book = dao.selectBook(name);
		if(book == null) {
			System.out.println("해당 책은 등록되어 있지 않습니다.");
		}else {
			dao.returnBook(name);
		}
	}
	private void insertBookInfo() {
		BookVO book = inputBookInfo();
		dao.insert(book);
	}
	private void end() {
		System.out.println("프로그램이 종료되었습니다.");
	}
	
	private String inputBookName() {
		String name = null;
		System.out.print("책제목>");
		name = scanner.nextLine();
		return name;
	}
	
	private String inputBookKeyword() {
		String keyword = null;
		System.out.print("검색내용>");
		keyword = scanner.nextLine();
		return keyword;
	}
	
	private BookVO inputBookInfo() {
		BookVO book = new BookVO();
		System.out.print("책제목>");
		book.setBookName(scanner.nextLine());
		System.out.print("저자명>");
		book.setBookWriter(scanner.nextLine());
		System.out.print("책내용>");
		book.setBookContent(scanner.nextLine());
		return book;
	}
}
	
	

		
