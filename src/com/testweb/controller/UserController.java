package com.testweb.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.xml.internal.bind.v2.runtime.Location;
import com.testweb.user.service.UserDeleteServiceImpl;
import com.testweb.user.service.UserJoinServiceImpl;
import com.testweb.user.service.UserLoginServiceImpl;
import com.testweb.user.service.UserService;
import com.testweb.user.service.UserUpdateServiceImpl;


@WebServlet("*.user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public UserController() {
        super();
     
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dispatchServlet(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dispatchServlet(request, response);
		
	}
	protected void dispatchServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		//한글처리
		request.setCharacterEncoding("UTF-8");
		
		//요청 분기 작업
		
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());
		
		System.out.println(command);
		
		//부모 인터페이스로 멤변수 처리
		UserService service;
		
		if(command.equals("/user/mypage.user")) {
			
			request.getRequestDispatcher("user_mypage.jsp").forward(request, response);

		}else if(command.equals("/user/join.user")) {//회원 가입 화면 처리
			request.getRequestDispatcher("user_join.jsp").forward(request, response);
		
		}else if(command.equals("/user/delete.user")) {//회원 탈퇴
			service = new UserDeleteServiceImpl();
			int result = service.execute(request, response);
			
			if(result == 1) { // 탈퇴 완료
				response.sendRedirect("login.user");
			}else { //비밀번호가 틀린경우
				request.setAttribute("msg", "비밀번호를 다시 입력 하세요.");
				request.getRequestDispatcher("delete.user").forward(request, response);
			}
			
			
		}else if(command.equals("/user/joinForm.user")) {//회원 가입
			
			service = new UserJoinServiceImpl();
			int result = service.execute(request, response);
			
			if(result == 0) { //중복 
				request.setAttribute("msg", "중복된 아이디 입니다.");
				request.getRequestDispatcher("join.user").forward(request, response);
			}else { //중복 x
				response.sendRedirect("login.user");
			}
			
		}else if(command.equals("/user/login.user")) { //로그인 화면
			
			request.getRequestDispatcher("user_login.jsp").forward(request, response);
			
		}else if(command.equals("/user/loginForm.user")) { //로그인
			
			service = new UserLoginServiceImpl();
			int result = service.execute(request, response);
			
			if(result == 0 ) {//로그인 성공
				response.sendRedirect("mypage.user");
			}else {
				request.setAttribute("msg", "아이디와 비밀번호를 확인해주세요");
				request.getRequestDispatcher("login.jsp").forward(request, response);
				
			}
			
		}else if(command.equals("/user/update.user")) {
			
			request.getRequestDispatcher("user_mypageinfo.jsp").forward(request, response);
			
		}else if(command.equals("/user/updateForm.user")) {
			service = new UserUpdateServiceImpl();
			int result = service.execute(request, response);
			
			if(result == 1) {
				
				response.setContentType("text/html; charset = UTF-8 ");
				PrintWriter out = response.getWriter();
				
				out.println("<script>");
				out.println("alert('회원정보 수정 완료입니다.');");
				out.println("location.href ='user_mypage.jsp'");
				out.println("</script>");
			}else {
				response.sendRedirect("mypage.user");
			}
		}
	}

}
