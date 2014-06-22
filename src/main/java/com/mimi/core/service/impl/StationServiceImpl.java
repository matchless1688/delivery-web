package com.mimi.core.service.impl;

import org.apache.log4j.Logger;

import com.mimi.core.dao.impl.StationDaoImpl;
import com.mimi.core.service.StationService;
import com.mimi.model.Page;
import com.mimi.model.Station;

public class StationServiceImpl implements StationService{
	
	private static final Logger log=Logger.getLogger(StationServiceImpl.class);
	
	private StationDaoImpl stationDao;

	@Override
	public void addOneStation(Station station) {
		log.info("one station add!");
		stationDao.addOneStation(station);
	}

	@Override
	public void deleteStationById(int id) {
		stationDao.deleteStationById(id);
	}

	@Override
	public Station queryStation(int id) {
		return stationDao.queryStation(id);
	}

	@Override
	public Station queryStationByName(String name) {
		return stationDao.queryStationByName(name);
	}

	@Override
	public Page queryStationList(Station station, Page page) {
		int count = stationDao.queryStationAmount(station);
		page.setCount(count);
		if(page.getCount() == 0) {
			return page;
		}
		return stationDao.queryStationList(station, page);
	}

	@Override
	public boolean nameIsSingle(String name, String id) {
		return stationDao.nameIsSingle(name, id);
	}

	@Override
	public void updateStation(Station station) {
		stationDao.updateStation(station);
	}

	@Override
	public int queryStationAmount(Station station) {
		return stationDao.queryStationAmount(station);
	}

	public void setStationDao(StationDaoImpl stationDao) {
		this.stationDao = stationDao;
	}
}
