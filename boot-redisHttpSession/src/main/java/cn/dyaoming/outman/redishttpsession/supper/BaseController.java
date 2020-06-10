package cn.dyaoming.outman.redishttpsession.supper;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseController {

	@Autowired
	protected HttpSession session;

	protected void setUserName(String name) {
		System.out.println(session.getId());
		session.setAttribute("name", name);
	}

	protected Object getUserName() {
		System.out.println("本次访问sessionId" + session.getId());
		return session.getAttribute("name");
	}

}
