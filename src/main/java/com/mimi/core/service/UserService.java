package com.mimi.core.service;

import com.mimi.model.Page;
import com.mimi.model.User;

public abstract interface UserService {
	public void addOneUser(User User);

	public void deleteUserById(int id);

	public User queryUser(int id);

	public User queryUserByUserName(String userName);

	public Page queryUserList(User user, Page page);

	public boolean aliasIsSingle(String alias, String userId);

	public void updateUser(User User);

	public int queryUserAmount();

	public User login(User user);

	public User queryByIMEI(String imei);

}