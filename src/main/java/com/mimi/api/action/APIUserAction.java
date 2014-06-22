package com.mimi.api.action;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.mimi.common.Constants;
import com.mimi.core.service.UserService;
import com.mimi.core.web.SuperAction;
import com.mimi.model.Response;
import com.mimi.model.User;
import com.opensymphony.xwork2.ModelDriven;
public class APIUserAction extends SuperAction implements ModelDriven<User>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(APIUserAction.class);
	private User user = new User();
	
	@Autowired
	private UserService userServiceImpl;
	

	public void setUserServiceImpl(UserService userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}
	 
	public String register() throws IOException
	{
		
		String userName = request.getParameter("userName");
		String pwd = request.getParameter("pwd");
		Response rsp = new Response();
		try{
			User u = userServiceImpl.queryUserByUserName(userName);
			if(u!=null)
			{
				rsp.setKey(1);
				return null;
			}
			user.setUserName(userName);
			user.setStatus(Constants.Common.STATUS_1);
			user.setPwd(pwd);
			userServiceImpl.addOneUser(user) ;
			rsp.setUser(user);
			
	 	}catch(Exception e)
	 	{
	 		rsp.setKey(9);
	 		log.error(e.getMessage(), e);
	 		rsp.setMessage(e.getMessage());
	 	}
	 	response.setContentType("text/json;charset=UTF-8");
	 	response.getWriter().print(new Gson().toJson(rsp));
		return null;
	}

	public String login() throws IOException
	{
		 
		
		Response rsp = new Response();
		User target = userServiceImpl.login(user);
		if(target == null)
		{
			rsp.setKey(2);
		}
		response.setContentType("text/json;charset=UTF-8");
		rsp.setUser(target) ;
		response.getWriter().print(new Gson().toJson(rsp));
		
		return null;
	}
	
	public String count() throws IOException
	{
		int count = userServiceImpl.queryUserAmount(user);
		response.getWriter().print(count);
		return null;
	}
	
	public String modifyPassword() throws IOException
	{
		Response rsp = new Response();
		User target = userServiceImpl.queryUser(user.getId());
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		if(!target.getPwd().equals(oldPassword))
		{
			rsp.setKey(2);
		}
		target.setPwd(newPassword);
		userServiceImpl.updateUser(target);
		response.getWriter().print(rsp);
		return null;
	}
	
	public String modifyUserName() throws IOException
	{
		String userName = request.getParameter("user.userName");
		Response rsp = new Response();
		User active = userServiceImpl.queryUser(user.getId());
		active.setUserName(userName);
		userServiceImpl.updateUser(active);
		response.getWriter().print(new Gson().toJson(rsp));
		return null;
	} 
	 
	public void getByUserId() throws IOException
	{
		String id = request.getParameter("id");
		User target = userServiceImpl.queryUser(Integer.valueOf(id));
		response.setContentType("text/json;charset=UTF-8");
		response.getWriter().print(new Gson().toJson(target));
	}
	 
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getUser() {
		return user;
	}
    
}
