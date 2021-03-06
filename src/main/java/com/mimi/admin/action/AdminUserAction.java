package com.mimi.admin.action;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.mimi.core.service.UserService;
import com.mimi.core.web.SuperAction;
import com.mimi.model.Page;
import com.mimi.model.User;
import com.mimi.util.SystemUtil;
import com.opensymphony.xwork2.ModelDriven;

public class AdminUserAction extends SuperAction implements ModelDriven<User> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private User user = new User();

	@Autowired
	private UserService userServiceImpl;

	public void setUserServiceImpl(UserService userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}

	public String manage() throws IOException {
		String pageIndex = request.getParameter("currentPage");
		int currentPage = 1;
		if (!SystemUtil.isEmpty(pageIndex)) {
			currentPage = Integer.parseInt(pageIndex);
		}
		Page page = new Page();
		page.setCurrentPage(currentPage);
		page.setOrder("id");
		if (user == null)
			user = new User();

		userServiceImpl.queryUserList(user, page);
		request.setAttribute("page", page);
		return "manage";
	}

	public String search() throws IOException {
		Page page = new Page();
		page.setOrder("id");
		if (user == null) {
			user = new User();
		}
		userServiceImpl.queryUserList(user, page);
		request.setAttribute("page", page);
		request.setAttribute("user", user);
		return "manage";
	}

	public void delete() throws IOException {
		String id = request.getParameter("id");
		userServiceImpl.deleteUserById(Integer.valueOf(id));
		response.getWriter().print(1);
	}
	
	public String add() {
		userServiceImpl.addOneUser(user);
		user = new User();
		Page page = new Page();
		page.setOrder("id");
		userServiceImpl.queryUserList(user, page);
		request.setAttribute("page", page);
		request.setAttribute("user", user);
		return "manage";
	}
	
	public User getModel() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}

}
