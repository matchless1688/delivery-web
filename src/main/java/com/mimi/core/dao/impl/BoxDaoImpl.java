package com.mimi.core.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;

import com.mimi.model.Box;
import com.mimi.model.Page;
import com.mimi.util.SystemUtil;

public class BoxDaoImpl extends HibernateDaoSupport{

	public void addOneBox(Box box) {
		getHibernateTemplate().save(box);
	}
	
	public void deleteBoxById(int id) {
		Assert.notNull(id, "deleteBoxById>id");
		Box box = new Box();
		box.setId(id);
		getHibernateTemplate().delete(box);
	}
	
	public Box queryBox(int id) {
		Assert.notNull(id, "queryBoxById>id");
		Criteria c = getSession().createCriteria(Box.class);
		c.add(Restrictions.eq("id", id));
		return (Box) c.uniqueResult();
	}
	
	public Box queryBoxByStationId(int stationId) {
		Assert.notNull(stationId, "queryBoxByStationId>stationId");
		Criteria c = getSession().createCriteria(Box.class).add(
				Restrictions.eq("stationId", stationId));
		return (Box) c.uniqueResult();
	}
	
	public Page queryBoxList(Box box, Page page) {
		Criteria criteria = mapingParam(box);
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
	
	public void updateBox(Box box) {
		getHibernateTemplate().merge(box);
	}
	
	public int queryBoxAmount(Box box) {
		Criteria criteria = mapingParam(box);
		return Integer.valueOf(criteria.setProjection(Projections.rowCount())
				.uniqueResult().toString());
	}
	
	private Criteria mapingParam(Box box) {
		Criteria criteria = getSession().createCriteria(Box.class);
		if (!SystemUtil.isEmpty(box.getStationId())) {
			criteria.add(Restrictions.eq("stationId", box.getStationId()));
		}
		if (!SystemUtil.isEmpty(box.getStatus())) {
			criteria.add(Restrictions.eq("status", box.getStatus()));
		}
		return criteria;
	}
}
