package com.testweb.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.testweb.user.model.UserDAO;
import com.testweb.user.model.UserVO;

public class UserJoinServiceImpl implements UserService{
	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String phone = request.getParameter("number1") + 
			   request.getParameter("number2") + request.getParameter("number3");
		String email = request.getParameter("email");
		String add_b = request.getParameter("addr-basic");
		String add_d = request.getParameter("addr-detail");
		
		UserDAO dao = UserDAO.getInstance();
		UserVO vo;
		int result = dao.checkId(id);
		
		if(result == 0) {
			return 0;
			
		}else {
			vo = new UserVO(id, name, pw, email, phone, add_b, add_d);
			dao.join(vo);
			
			return 1;
		}
	}
}
