package com.mimi.admin.action;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.mimi.core.service.StationService;
import com.mimi.core.web.SuperAction;
import com.mimi.model.Page;
import com.mimi.model.Station;
import com.mimi.util.SystemUtil;
import com.opensymphony.xwork2.ModelDriven;

public class AdminStationAction extends SuperAction implements ModelDriven<Station>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Station station = new Station();
	
	@Autowired
	private StationService stationServiceImpl;
	
	public void setStationServiceImpl(StationService stationServiceImpl) {
		this.stationServiceImpl = stationServiceImpl;
	}
	
	public String manage() throws IOException {
		String pageIndex = request.getParameter("currentPage");
		int currentPage = 1;
		if (!SystemUtil.isEmpty(pageIndex)) {
			currentPage = Integer.parseInt(pageIndex);
		}
		Page page = new Page();
		page.setCurrentPage(currentPage);
		page.setOrder("id");
		if (station == null)
			station = new Station();

		stationServiceImpl.queryStationList(station, page);
		request.setAttribute("page", page);
		return "manage";
	}

	public String search() throws IOException {
		Page page = new Page();
		page.setOrder("id");
		if (station == null) {
			station = new Station();
		}
		stationServiceImpl.queryStationList(station, page);
		request.setAttribute("page", page);
		request.setAttribute("station", station);
		return "manage";
	}

	public void delete() throws IOException {
		String id = request.getParameter("id");
		stationServiceImpl.deleteStationById(Integer.valueOf(id));
		response.getWriter().print(1);
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
