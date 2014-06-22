package com.mimi.core.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;

import com.mimi.model.Page;
import com.mimi.model.User;
import com.mimi.util.SystemUtil;


public class UserDaoImpl extends HibernateDaoSupport{

	public void addOneUser(User User) {
		getHibernateTemplate().save(User);
	}

	public void deleteUserById(int id) {
		Assert.notNull(id, "deleteUserById>id");
		User user = new User();
		user.setId(id);
		getHibernateTemplate().delete(user);
	}

	 

	public User queryUser(int id) {
		Assert.notNull(id, "queryUser>id");
		Criteria c = getSession().createCriteria(User.class);
		c.add(Restrictions.eq("id", id));
		return (User) c.uniqueResult();
	}

	public User queryUserByUserName(String userName) {
		Assert.notNull(userName, "queryUserByUserName>userName");
		Criteria c = getSession().createCriteria(User.class).add(
				Restrictions.eq("userName", userName));
		return (User) c.uniqueResult();
	}

	public void updateUser(User user) {

		getHibernateTemplate().merge(user);
	}


	public boolean aliasIsSingle(String userName,String id) {
		Criteria c = getSession().createCriteria(User.class).add(Restrictions.eq("userName", userName)).add(Restrictions.ne("id", id));
		List<User> list=c.list();
		if (!list.isEmpty()) {
			return false;
		}
		return true;
	}

	public User queryByIMEI(String imei) {
		Criteria c = getSession().createCriteria(User.class).add(Restrictions.eq("imei", imei));
		return (User) c.uniqueResult();
	}

	public Page queryUserList(User user, Page page) {
		 
		Criteria criteria = mapingParam( user);
	    criteria.setFirstResult((page.getCurrentPage() - 1) * page.size);
	    criteria.setMaxResults(page.size);
	    criteria.addOrder(Order.desc(page.getOrder()));
	    try{
	      page.setDataList(criteria.list());
	    }catch(Exception e)
	    {
	    	e.printStackTrace();
	    }
	    return page;
	}
	public int queryUserAmount(User user) {
		 Criteria criteria = mapingParam( user);
		 return Integer.valueOf(criteria.setProjection(Projections.rowCount()).uniqueResult().toString());
	}
	private Criteria mapingParam(User user)
	  {
	    Criteria criteria = getSession().createCriteria(User.class);
	    if (!SystemUtil.isEmpty(user.getUserName()))
	    {
	      criteria.add(Restrictions.eq("userName", user.getUserName()));
	    }
	    if (!SystemUtil.isEmpty(user.getId()))
	    {
	      criteria.add(Restrictions.eq("id",user.getId()));
	    }
	    return criteria;
	  }



}
