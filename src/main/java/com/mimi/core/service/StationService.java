package com.mimi.core.service;

import com.mimi.model.Page;
import com.mimi.model.Station;

public abstract interface StationService {

	public void addOneStation(Station station);

	public void deleteStationById(int id);

	public Station queryStation(int id);

	public Station queryStationByName(String name);

	public Page queryStationList(Station station, Page page);

	public boolean nameIsSingle(String name, String id);

	public void updateStation(Station station);

	public int queryStationAmount(Station station);
}
