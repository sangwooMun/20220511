package com.yedam.java.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import library.com.yedam.dao.DataSource;


public class LibraryServiceImpl implements libraryService {
	
	private DataSource dao = DataSource.getInstance();
	
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	
	private void close() { // 연결의 반대, 닫을때
		try {
			if(rs != null) rs.close();
			if(psmt != null) psmt.close();
			if(conn != null) conn.close();
		}catch(SQLException e) {
			e.printStackTrace();;
		}
	}

	@Override
	public List<BookVO> selectAllList() {
		List<BookVO> list = new ArrayList<BookVO>();
		try {
			conn = dao.getConnection();
			String sql = "SELECT * FROM LIBRARY";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				BookVO book = new BookVO();
				book.setBookName(rs.getString("bookname"));
				book.setName(rs.getString("name"));
				book.setContent(rs.getString("content"));
				book.setRent(rs.getString("rent"));
				
				list.add(book);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}
		
		return list;
	}

	@Override
	public BookVO selectBookInfo(String name) {
		BookVO book = new BookVO();
		try {
			conn = dao.getConnection();
			String sql = "SELECT * FROM LIBRARY WHERE BOOKNAME = ?";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, name);;
			rs = psmt.executeQuery();
			if(rs.next()) {
				book.setBookName(rs.getString("bookname"));
				book.setName(rs.getString("name"));
				book.setContent(rs.getString("content"));
				book.setRent(rs.getString("rent"));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}
		
		return book;
	}

	@Override
	public List<BookVO> selectBookList(String content) {
		List<BookVO> list = new ArrayList<BookVO>();
		try {
			conn = dao.getConnection();
			String sql = "SELECT * FROM LIBRARY WHERE CONTENT LIKE %?%";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, content);
			rs = psmt.executeQuery();
			while(rs.next()) {
				BookVO book = new BookVO();
				book.setBookName(rs.getString("bookname"));
				book.setName(rs.getString("name"));
				book.setContent(rs.getString("content"));
				book.setRent(rs.getString("rent"));
				
				list.add(book);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}
		
		return list;
	}

	@Override
	public List<BookVO> selectRentalBookList() {
		List<BookVO> list = new ArrayList<BookVO>();
		try {
			conn = dao.getConnection();
			String sql = "SELECT * FROM LIBRARY WHERE RENT = 대여가능";
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				BookVO book = new BookVO();
				book.setBookName(rs.getString("bookname"));
				book.setName(rs.getString("name"));
				book.setContent(rs.getString("content"));
				book.setRent(rs.getString("rent"));
				
				list.add(book);
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}finally{
			close();
		}
		
		return list;
	}

	@Override
	public void rentalBook(String name) {
		try {
			// db 새로운 연결
			conn = dao.getConnection();
			String sql = "UPDATE LIBRARY SET RENT = 1 WHERE BOOKNAME = ?";
			// db에 전달할 sql문을 만드는 것
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, name);
			// db에서 실행된 sql문의 결과를 반환
			int result = psmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("해당 책이 정상적으로 대여 완료");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}

	}

	@Override
	public void returnBook(String name) {
		try {
			// db 새로운 연결
			conn = dao.getConnection();
			String sql = "UPDATE LIBRARY SET RENT = 0 WHERE BOOKNAME = ?";
			// db에 전달할 sql문을 만드는 것
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, name);
			// db에서 실행된 sql문의 결과를 반환
			int result = psmt.executeUpdate();
			
			if(result > 0) {
				System.out.println("해당 책이 정상적으로 대여 완료");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}

	}

	@Override
	public void insertBook(BookVO book) {
		
		try {
			conn = dao.getConnection();
			String sql = "INSERT INTO LIBRARYSYS VALUES(?,?,?,?)";
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, book.getBookName());
			psmt.setString(2, book.getName());
			psmt.setString(3, book.getContent());
			psmt.setString(4, book.getRent());
			
			int result = psmt.executeUpdate();
			if(result > 0) {
				System.out.println("정상 등록");
			}
			psmt.executeUpdate();
		}catch(SQLException e) {
			e.printStackTrace();
		}

	}

}
