package com.testweb.user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import JdbcUil.java.JdbcUtil;

public class UserDAO {
	
	//singleton형식으로 지정
	private static UserDAO instance = new UserDAO();
	
	//생성자도 직접 생성할수 없게 private 접근자 사용
	private UserDAO() {
		
		try {
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//커넥션 풀을 얻는 작업(초기설정 정보가 저장되는 객체)
			InitialContext ctx = new InitialContext();
		    ds = (DataSource)ctx.lookup("java:comp/env/jdbc/oracle");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	//3. 외부에서 객체생성을 요구할 때 getter 메서드를 통해 1번의 객체를 반환

	public static UserDAO getInstance() {
		return instance;
	}
	
	private DataSource ds;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	
	public int join (UserVO vo) {
		int result = 0;
		
		String sql = "insert into users2 (id,name,password,email,phone,address1,address2)"
				+ "values(?,?,?,?,?,?,?)";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getPassWord());
			pstmt.setString(4, vo.getEmail());
			pstmt.setString(5, vo.getPhone());
			pstmt.setString(6, vo.getAddress());
			pstmt.setString(7, vo.getAddress2());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, null);
		}
		
		return result;
		
	}
	
	public int checkId(String id) {
		
		int result = 0;
		String sql = "select * from users2 where id = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {// 중복
				result = 0;
			}else {
				result = 1;
			}
			
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return result;
	}
	
	public UserVO login(String id, String pw) {
		
		String sql = "select * from users2 where id = ? and password = ?";
		UserVO vo = null;
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				vo = new UserVO();
				vo.setId(rs.getString("id"));
				vo.setPassWord(rs.getString("password"));
				vo.setName(rs.getString("name"));
				vo.setPhone(rs.getString("phone"));
				vo.setEmail(rs.getString("email"));
				vo.setAddress(rs.getString("address1"));
				vo.setAddress2(rs.getString("address2"));
			}else {
				vo = null;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, rs);
		}
		
		return vo;
	}
	
	
	public int delete(String id) {
		
		int result = 0;
		
		String sql = "delete from users2 where id = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,id);
			
			result = pstmt.executeUpdate();
	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(conn, pstmt, null);
		}
		
		return result;
	}
	
	
	public int update(UserVO vo) {
		
		int result = 0;
		
		String sql = "update users2 "
					+ "set name = ?, "
						+ "password = ?, "
						+ "email = ?, "
						+ "phone = ?, "
						+ "address1 = ?, "
						+ "address2 = ? "
					+ "where id = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassWord());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getPhone());
			pstmt.setString(5, vo.getAddress());
			pstmt.setString(6, vo.getAddress2());
			pstmt.setString(7, vo.getId());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			JdbcUtil.close(conn, pstmt, null);
		}

		return result;
	}
	
	
	
	
}
