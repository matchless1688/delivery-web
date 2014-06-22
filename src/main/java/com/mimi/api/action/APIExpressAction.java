package com.mimi.api.action;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;
import com.mimi.core.service.ExpressService;
import com.mimi.core.web.SuperAction;
import com.mimi.model.Express;
import com.mimi.model.Response;
import com.opensymphony.xwork2.ModelDriven;

public class APIExpressAction extends SuperAction implements ModelDriven<Express>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static Logger log = Logger.getLogger(APIExpressAction.class);
	private Express express = new Express();
	
	@Autowired
	private ExpressService expressServiceImpl;
	
	public String add() throws IOException {
		String barCode = request.getParameter("barCode");
		String tdjh = request.getParameter("tdjh");
		String status = request.getParameter("status");
		String deliTel = request.getParameter("deliTel"); 
		String stationId = request.getParameter("stationId");
		String boxId = request.getParameter("boxId");
		String expressCompanyCode = request.getParameter("expressCompanyCode");
		String ownerPhone = request.getParameter("ownerPhone");
		Response rsp = new Response();
		try{
			express.setBarCode(barCode);
			express.setTdjh(tdjh);
			express.setStatus(status);
			express.setDeliTel(deliTel);
			express.setStationId(Integer.valueOf(stationId));
			express.setBoxId(Integer.valueOf(boxId));
			express.setExpressCompanyCode(expressCompanyCode);
			express.setOwnerPhone(ownerPhone);
			expressServiceImpl.addOneExpress(express);
			rsp.setExpress(express);
			
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
		int count = expressServiceImpl.queryExpressAmount(express);
		response.getWriter().print(count);
		return null;
	}
	
	public void setExpressServiceImpl(ExpressService expressServiceImpl) {
		this.expressServiceImpl = expressServiceImpl;
	}

	@Override
	public Express getModel() {
		return express;
	}

	public Express getExpress() {
		return express;
	}

	public void setExpress(Express express) {
		this.express = express;
	}

}
