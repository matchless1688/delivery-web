package com.mimi.api.action;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.mimi.core.service.BoxService;
import com.mimi.core.web.SuperAction;
import com.mimi.model.Box;
import com.mimi.model.Response;
import com.opensymphony.xwork2.ModelDriven;

public class APIBoxAction extends SuperAction implements ModelDriven<Box>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(APIBoxAction.class);
	private Box box = new Box();
	
	@Autowired
	private BoxService boxServiceImpl;
	
	public String add() throws IOException {
		String length = request.getParameter("length");
		String height = request.getParameter("height");
		String width = request.getParameter("width");
		String stationId = request.getParameter("stationId");
		Response rsp = new Response();
		try{
			box.setLength(length);
			box.setHeight(height);
			box.setWidth(width);
			box.setStationId(Integer.valueOf(stationId));
			boxServiceImpl.addOneBox(box);
			rsp.setBox(box);
			
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
		int count = boxServiceImpl.queryBoxAmount(box);
		response.getWriter().print(count);
		return null;
	}
	
	public void setBoxServiceImpl(BoxService boxServiceImpl) {
		this.boxServiceImpl = boxServiceImpl;
	}

	@Override
	public Box getModel() {
		return box;
	}

	public Box getBox() {
		return box;
	}

	public void setBox(Box box) {
		this.box = box;
	}

}
