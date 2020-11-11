package com.testweb.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.testweb.user.model.UserDAO;
import com.testweb.user.model.UserVO;

public class UserDeleteServiceImpl implements UserService {

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		
		UserDAO dao = UserDAO.getInstance();
		
		//세션에서 id,password 가져옴
		HttpSession session = request.getSession();
		UserVO vo = (UserVO)session.getAttribute("user");
		String id = vo.getId();
		String pw = request.getParameter("pw");
		
		UserVO result = dao.login(id, pw);
		
		if(result != null) {// 비밀번호가 맞는경우 삭제 진행
			dao.delete(id);
			session.invalidate();
			return 1;
			
		}else {
			return 0;

		}
		

	}

}
