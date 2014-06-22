package com.mimi.admin.action;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.mimi.core.service.ExpressService;
import com.mimi.core.web.SuperAction;
import com.mimi.model.Express;
import com.mimi.model.Page;
import com.mimi.util.SystemUtil;
import com.opensymphony.xwork2.ModelDriven;

public class AdminExpressAction extends SuperAction implements ModelDriven<Express>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Express express = new Express();
	
	@Autowired
	private ExpressService expressServiceImpl;
	
	public void setExpressServiceImpl(ExpressService expressServiceImpl) {
		this.expressServiceImpl = expressServiceImpl;
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
		if (express == null)
			express = new Express();

		expressServiceImpl.queryExpressList(express, page);
		request.setAttribute("page", page);
		return "manage";
	}

	public String search() throws IOException {
		Page page = new Page();
		page.setOrder("id");
		if (express == null) {
			express = new Express();
		}
		expressServiceImpl.queryExpressList(express, page);
		request.setAttribute("page", page);
		request.setAttribute("express", express);
		return "manage";
	}

	public void delete() throws IOException {
		String id = request.getParameter("id");
		expressServiceImpl.deleteExpressById(Integer.valueOf(id));
		response.getWriter().print(1);
	}

	public Express getExpress() {
		return express;
	}

	public void setExpress(Express express) {
		this.express = express;
	}

	@Override
	public Express getModel() {
		return express;
	}

}
