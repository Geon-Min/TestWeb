package com.testweb.board.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.testweb.board.model.BoardDAO;
import com.testweb.board.model.BoardVO;

public class GetListServiceImpl implements BoardService{
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		BoardDAO dao = BoardDAO.getInstance();
		ArrayList<BoardVO> list = dao.getList();
		
		request.setAttribute("list", list);
		
	}
}
