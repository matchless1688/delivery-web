package com.mimi.core.service;

import com.mimi.model.Express;
import com.mimi.model.Page;

public abstract interface ExpressService {

	public void addOneExpress(Express express);

	public void deleteExpressById(int id);

	public Express queryExpress(int id);

	public Express queryExpressByStationId(int stationId);
	
	public Express queryExpressByBoxId(int boxId);

	public Page queryExpressList(Express express, Page page);

	public void updateExpress(Express express);

	public int queryExpressAmount(Express express);
}
