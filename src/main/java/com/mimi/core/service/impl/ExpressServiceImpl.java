package com.mimi.core.service.impl;

import org.apache.log4j.Logger;

import com.mimi.core.dao.impl.ExpressDaoImpl;
import com.mimi.core.service.ExpressService;
import com.mimi.model.Express;
import com.mimi.model.Page;

public class ExpressServiceImpl implements ExpressService{

	private static final Logger log=Logger.getLogger(ExpressServiceImpl.class);
	
	private ExpressDaoImpl expressDao;
	
	@Override
	public void addOneExpress(Express express) {
		log.info("add one express!");
		expressDao.addOneExpress(express);
	}

	@Override
	public void deleteExpressById(int id) {
		expressDao.deleteExpressById(id);
	}

	@Override
	public Express queryExpress(int id) {
		return expressDao.queryExpress(id);
	}

	@Override
	public Express queryExpressByStationId(int stationId) {
		return expressDao.queryExpressByStationId(stationId);
	}

	@Override
	public Express queryExpressByBoxId(int boxId) {
		return expressDao.queryExpressByBoxId(boxId);
	}

	@Override
	public Page queryExpressList(Express express, Page page) {
		int count = expressDao.queryExpressAmount(express);
		page.setCount(count);
		if(page.getCount() == 0) {
			return page;
		}
		return expressDao.queryExpressList(express, page);
	}

	@Override
	public void updateExpress(Express express) {
		expressDao.updateExpress(express);
	}

	@Override
	public int queryExpressAmount(Express express) {
		return expressDao.queryExpressAmount(express);
	}

	public void setExpressDao(ExpressDaoImpl expressDao) {
		this.expressDao = expressDao;
	}

}
