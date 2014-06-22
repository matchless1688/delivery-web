package com.mimi.core.service;

import com.mimi.model.Box;
import com.mimi.model.Page;

public abstract interface BoxService {

	public void addOneBox(Box box);

	public void deleteBoxById(int id);

	public Box queryBox(int id);

	public Box queryBoxByStationId(int stationId);

	public Page queryBoxList(Box box, Page page);

	public void updateBox(Box box);

	public int queryBoxAmount(Box box);
}
