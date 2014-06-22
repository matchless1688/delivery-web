package com.mimi.admin.action;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;

import com.mimi.core.service.BoxService;
import com.mimi.core.web.SuperAction;
import com.mimi.model.Box;
import com.mimi.model.Page;
import com.mimi.util.SystemUtil;
import com.opensymphony.xwork2.ModelDriven;

public class AdminBoxAction extends SuperAction implements ModelDriven<Box>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Box box = new Box();
	
	@Autowired
	private BoxService boxServiceImpl;
	
	public void setBoxServiceImpl(BoxService boxServiceImpl) {
		this.boxServiceImpl = boxServiceImpl;
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
		if (box == null)
			box = new Box();

		boxServiceImpl.queryBoxList(box, page);
		request.setAttribute("page", page);
		return "manage";
	}

	public String search() throws IOException {
		Page page = new Page();
		page.setOrder("id");
		if (box == null) {
			box = new Box();
		}
		boxServiceImpl.queryBoxList(box, page);
		request.setAttribute("page", page);
		request.setAttribute("box", box);
		return "manage";
	}

	public void delete() throws IOException {
		String id = request.getParameter("id");
		boxServiceImpl.deleteBoxById(Integer.valueOf(id));
		response.getWriter().print(1);
	}

	public Box getBox() {
		return box;
	}

	public void setBox(Box box) {
		this.box = box;
	}

	@Override
	public Box getModel() {
		return box;
	}

}
