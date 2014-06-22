package com.mimi.core.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;

import com.mimi.model.Page;
import com.mimi.model.Station;
import com.mimi.model.User;
import com.mimi.util.SystemUtil;

public class StationDaoImpl extends HibernateDaoSupport {
	
	public void addOneStation(Station station) {
		getHibernateTemplate().save(station);
	}
	
	public void deleteStationById(int id) {
		Assert.notNull(id, "deleteStationById>id");
		Station station = new Station();
		station.setId(id);
		getHibernateTemplate().delete(station);
	}
	
	public Station queryStation(int id) {
		Assert.notNull(id, "queryStationById>id");
		Criteria c = getSession().createCriteria(Station.class);
		c.add(Restrictions.eq("id", id));
		return (Station) c.uniqueResult();
	}
	
	public Station queryStationByName(String name) {
		Assert.notNull(name, "queryStationByName>name");
		Criteria c = getSession().createCriteria(Station.class).add(
				Restrictions.eq("name", name));
		return (Station) c.uniqueResult();
	}
	
	public Page queryStationList(Station station, Page page) {
		Criteria criteria = mapingParam(station);
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
	
	public void updateStation(Station station) {
		getHibernateTemplate().merge(station);
	}
	
	public int queryStationAmount(Station station) {
		Criteria criteria = mapingParam(station);
		return Integer.valueOf(criteria.setProjection(Projections.rowCount())
				.uniqueResult().toString());
	}
	
	public boolean nameIsSingle(String name, String id) {
		Criteria c = getSession().createCriteria(Station.class)
				.add(Restrictions.eq("name", name))
				.add(Restrictions.ne("id", id));
		@SuppressWarnings("unchecked")
		List<User> list = c.list();
		if (!list.isEmpty()) {
			return false;
		}
		return true;
	}
	
	private Criteria mapingParam(Station station) {
		Criteria criteria = getSession().createCriteria(Station.class);
		if (!SystemUtil.isEmpty(station.getName())) {
			criteria.add(Restrictions.eq("name", station.getName()));
		}
		return criteria;
	}
}
