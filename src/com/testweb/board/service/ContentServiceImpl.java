package com.testweb.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.testweb.board.model.BoardDAO;
import com.testweb.board.model.BoardVO;

public class ContentServiceImpl implements BoardService {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		
		BoardDAO dao = BoardDAO.getInstance();
		BoardVO vo = new BoardVO();
	
	}

}
