package com.testweb.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import JdbcUil.java.JdbcUtil;

public class BoardDAO {
	
	//1. 나자신의 객체를 생성해서 1개로 제한합니다. 싱글촌
	
	private static BoardDAO instance = new BoardDAO();
	
	//2. 객체도 생성할수 없게 제한접근자를 붇임
	private BoardDAO() {
		
		//드라이버로드
		//Class.forName("oracle.jdbc.driver.OracleDriver")
		//커넥션 풀을 얻는 작업(초기 설정 정보가 저장되는 객체)
		
		try {
			InitialContext ctx = new InitialContext();
			ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	//외부에서 getter메서드를 통해 1번의 객체를 반환
	public static BoardDAO getInstance() {
		return instance;
	}
	
	// DB연결 변수들을 상수로 선언;
	//private String url = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
	//private String uid = "JSP";
	//private String upw = "JSP";
	
	private DataSource ds;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	public void regist(String writer, String title, String content ) {
		
		String sql = "insert into board2 (bno, writer, title, content)"
				+ "values(bno_seq.nextval,?,?,?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "writer");
			pstmt.setString(2, "title");
			pstmt.setString(3,"content");
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, null);
		}
	}
	
	//목록
	/* 데이터베이스에서 번호를 내림차순으로 조회해서 list에 
	 * 담는 코드를 작성.
	 */
	
	public ArrayList<BoardVO> getList(){
		
		ArrayList<BoardVO> list = new ArrayList<>();
		
		String sql = "select * from board2 order by bno asc";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.executeQuery();
			
			while(rs.next()) {
				BoardVO vo = new BoardVO();
				vo.setBno(rs.getInt("bno"));
				vo.setWriter(rs.getString("writer"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setRegdate(rs.getTimestamp("regdate"));
				
				list.add(vo);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return list;
	}

	
	//상세보기
	/*
	 * 번호 기준으로 select 구문으로 조회해서 BoardVO 저장하고,
	 * vo 이름으로 화면에 데이터를 전달
	 * 
	 * request 저장
	 */
	
	public BoardVO getcontent(String bno) {
		
		BoardVO vo = new BoardVO();
		
		String sql = "select * from board2 where bno = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,"bno");
			
			pstmt.executeQuery();
			
			while(rs.next()) {
				vo.setBno(rs.getInt("bno"));
				vo.setWriter(rs.getString("writer"));
				vo.setTitle(rs.getString("title"));
				vo.setContent(rs.getString("content"));
				vo.setRegdate(rs.getTimestamp("regdate"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return vo;
	}
	
	
	
	
	
	
	
	
	
	
	
}
