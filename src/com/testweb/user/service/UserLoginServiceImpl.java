package com.testweb.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.User;

import com.testweb.user.model.UserDAO;
import com.testweb.user.model.UserVO;

public class UserLoginServiceImpl implements UserService {

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		System.out.println(id);
		System.out.println(pw);
		
		
		UserDAO dao = UserDAO.getInstance();
		UserVO user = dao.login(id, pw);
		System.out.println(user.toString());
		
		int result = 1;
		
		if(user != null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			result = 0;
			
		}else{
			result = 1;
		}
	return result; 
	}

}
