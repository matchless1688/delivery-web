package com.mimi.model;

import java.io.Serializable;

public class Station implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
	private String address;
	private String name;
	private String boxes;
	private String tdjh;
	private String tdd;
	private String createDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBoxes() {
		return boxes;
	}
	public void setBoxes(String boxes) {
		this.boxes = boxes;
	}
	public String getTdjh() {
		return tdjh;
	}
	public void setTdjh(String tdjh) {
		this.tdjh = tdjh;
	}
	public String getTdd() {
		return tdd;
	}
	public void setTdd(String tdd) {
		this.tdd = tdd;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
}
