package com.testweb.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.testweb.user.model.UserDAO;
import com.testweb.user.model.UserVO;

public class UserUpdateServiceImpl implements UserService {

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String pw = request.getParameter("pw");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone1")+request.getParameter("phone2")+
			           request.getParameter("phone3");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		
		
		UserDAO dao = UserDAO.getInstance();
		
		UserVO vo = new UserVO(id, name, pw, email, phone, address1, address2);
		
		int result = dao.update(vo);
		
		if(result == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("user", vo);
			
		}
		return result;
	}

}
