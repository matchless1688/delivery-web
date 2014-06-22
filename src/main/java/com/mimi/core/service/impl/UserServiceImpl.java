package com.mimi.core.service.impl;

import org.apache.log4j.Logger;

import com.mimi.core.dao.impl.UserDaoImpl;
import com.mimi.core.service.UserService;
import com.mimi.model.Page;
import com.mimi.model.User;
import com.mimi.util.MD5;

public class UserServiceImpl implements UserService {

	private static final Logger log = Logger.getLogger(UserServiceImpl.class);

	private UserDaoImpl userDao;

	// @Transactional(propagation=Propagation.REQUIRED)
	public void addOneUser(User User) {
		log.info("one user register!");
		userDao.addOneUser(User);
	}

	// @Transactional(propagation=Propagation.REQUIRED)
	public void deleteUserById(int id) {
		userDao.deleteUserById(id);
	}

	// @Transactional(readOnly=true)
	public User queryUser(int id) {
		User User = userDao.queryUser(id);

		return User;
	}

	// @Transactional(propagation=Propagation.REQUIRED)
	public void updateUser(User User) {
		userDao.updateUser(User);
	}

	// @Transactional(propagation=Propagation.REQUIRED)
	public User login(User user) {
		log.info("一个用户登陆!");
		String password = user.getPwd();
		user = queryUserByUserName(user.getUserName());
		if (user != null) {

			if (!MD5.getMD5Encoding(password).equals(user.getPwd())) {
				return null;
			}

		}
		return user;
	}

	public void setUserDao(UserDaoImpl userDao) {
		this.userDao = userDao;
	}

	@Override
	public boolean aliasIsSingle(String alias, String userId) {
		return this.userDao.aliasIsSingle(alias, userId);
	}

	@Override
	public User queryByIMEI(String imei) {
		// TODO Auto-generated method stub
		return userDao.queryByIMEI(imei);
	}

	@Override
	public User queryUserByUserName(String userName) {
		// TODO Auto-generated method stub
		return userDao.queryUserByUserName(userName);
	}

	@Override
	public Page queryUserList(User user, Page page) {
		int count = this.userDao.queryUserAmount(user);
		page.setCount(Integer.valueOf(count));
		if (page.getCount() == 0) {
			return page;
		}
		return this.userDao.queryUserList(user, page);
	}

	// @Transactional(readOnly=true)
	public int queryUserAmount(User user) {
		return this.userDao.queryUserAmount(user);
	}

}
