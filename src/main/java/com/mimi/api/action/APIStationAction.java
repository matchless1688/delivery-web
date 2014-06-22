package com.mimi.api.action;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.mimi.core.service.StationService;
import com.mimi.core.web.SuperAction;
import com.mimi.model.Response;
import com.mimi.model.Station;
import com.opensymphony.xwork2.ModelDriven;

public class APIStationAction extends SuperAction implements ModelDriven<Station>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(APIStationAction.class);
	private Station station = new Station();
	
	@Autowired
	private StationService stationServiceImpl;
	
	public String add() throws IOException {
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String boxes = request.getParameter("boxes");
		Response rsp = new Response();
		try{
			Station s = stationServiceImpl.queryStationByName(name);
			if(s!=null)
			{
				rsp.setKey(1);
				return null;
			}
			station.setName(name);
			station.setAddress(address);
			station.setBoxes(boxes);
			stationServiceImpl.addOneStation(station) ;
			rsp.setStation(station);
			
	 	}catch(Exception e)
	 	{
	 		rsp.setKey(9);
	 		log.error(e.getMessage(), e);
	 		rsp.setMessage(e.getMessage());
	 	}
	 	response.setContentType("text/json;charset=UTF-8");
	 	response.getWriter().print(new Gson().toJson(rsp));
		return null;
	}
	
	public String count() throws IOException
	{
		int count = stationServiceImpl.queryStationAmount(station);
		response.getWriter().print(count);
		return null;
	}
	
	public void setStationServiceImpl(StationService stationServiceImpl) {
		this.stationServiceImpl = stationServiceImpl;
	}

	@Override
	public Station getModel() {
		return station;
	}

	public Station getStation() {
		return station;
	}

	public void setStation(Station station) {
		this.station = station;
	}

}
