package com.mimi.core.service.impl;

import org.apache.log4j.Logger;

import com.mimi.core.dao.impl.BoxDaoImpl;
import com.mimi.core.service.BoxService;
import com.mimi.model.Box;
import com.mimi.model.Page;

public class BoxServiceImpl implements BoxService{

	private static final Logger log=Logger.getLogger(BoxServiceImpl.class);
	
	private BoxDaoImpl boxDao;
	
	@Override
	public void addOneBox(Box box) {
		log.info("add one box!");
		boxDao.addOneBox(box);
	}

	@Override
	public void deleteBoxById(int id) {
		boxDao.deleteBoxById(id);
	}

	@Override
	public Box queryBox(int id) {
		return boxDao.queryBox(id);
	}

	@Override
	public Box queryBoxByStationId(int stationId) {
		return boxDao.queryBoxByStationId(stationId);
	}

	@Override
	public Page queryBoxList(Box box, Page page) {
		int count = boxDao.queryBoxAmount(box);
		page.setCount(count);
		if(page.getCount() == 0) {
			return page;
		}
		return boxDao.queryBoxList(box, page);
	}

	@Override
	public void updateBox(Box box) {
		boxDao.updateBox(box);
	}

	@Override
	public int queryBoxAmount(Box box) {
		return boxDao.queryBoxAmount(box);
	}

	public void setBoxDao(BoxDaoImpl boxDao) {
		this.boxDao = boxDao;
	}
	
}
