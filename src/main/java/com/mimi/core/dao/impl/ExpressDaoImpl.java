package com.mimi.core.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;

import com.mimi.model.Box;
import com.mimi.model.Express;
import com.mimi.model.Page;
import com.mimi.util.SystemUtil;

public class ExpressDaoImpl extends HibernateDaoSupport{

	public void addOneExpress(Express express) {
		getHibernateTemplate().save(express);
	}
	
	public void deleteExpressById(int id) {
		Assert.notNull(id, "deleteExpressById>id");
		Express express = new Express();
		express.setId(id);
		getHibernateTemplate().delete(express);
	}
	
	public Express queryExpress(int id) {
		Assert.notNull(id, "queryExpressById>id");
		Criteria c = getSession().createCriteria(Express.class);
		c.add(Restrictions.eq("id", id));
		return (Express) c.uniqueResult();
	}
	
	public Express queryExpressByStationId(int stationId) {
		Assert.notNull(stationId, "queryExpressByStationId>stationId");
		Criteria c = getSession().createCriteria(Express.class).add(
				Restrictions.eq("stationId", stationId));
		return (Express) c.uniqueResult();
	}
	
	public Express queryExpressByBoxId(int boxId) {
		Assert.notNull(boxId, "queryExpressByBoxId>boxId");
		Criteria c = getSession().createCriteria(Express.class).add(
				Restrictions.eq("boxId", boxId));
		return (Express) c.uniqueResult();
	}
	
	public Page queryExpressList(Express express, Page page) {
		Criteria criteria = mapingParam(express);
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
	
	public void updateExpress(Express express) {
		getHibernateTemplate().merge(express);
	}
	
	public int queryExpressAmount(Express express) {
		Criteria criteria = mapingParam(express);
		return Integer.valueOf(criteria.setProjection(Projections.rowCount())
				.uniqueResult().toString());
	}
	
	private Criteria mapingParam(Express express) {
		Criteria criteria = getSession().createCriteria(Express.class);
		return criteria;
	}
}
