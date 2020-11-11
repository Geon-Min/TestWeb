package com.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.testweb.board.service.BoardService;
import com.testweb.board.service.RegistServiceImpl;
import com.testweb.user.service.UserService;


@WebServlet("*.board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public BoardController() {
        super();
     
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dispatchServlet(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		dispatchServlet(request, response);
	}
	
	protected void dispatchServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		//요청분기 작성
		String uri = request.getRequestURI();
		String conPath = request.getContextPath();
		String command = uri.substring(conPath.length());
		
		System.out.println(command);
		
		//Service 부모타입 선언
		BoardService service;	
		
		if(command.equals("/board/bbs.board")) { //게시판 목차
			
			request.getRequestDispatcher("bbs.jsp").forward(request, response);
			
		}else if(command.equals("/board/write.board")) { //글쓰기 화면
			
			request.getRequestDispatcher("bbs_write.jsp").forward(request, response);
			
		
		}else if(command.equals("/board/regist.board")) {// 글 등록 요청 
			
			service = new RegistServiceImpl();
			service.execute(request, response);
			response.sendRedirect("bbs.board");
		}
		
	}

}
