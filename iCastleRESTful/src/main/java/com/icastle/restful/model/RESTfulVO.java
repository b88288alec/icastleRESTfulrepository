package com.icastle.restful.model;

import java.io.Serializable;

public class RESTfulVO implements Serializable{

	private Integer id;
	private String searchname;
	private java.sql.Date startdate;
	private java.sql.Date enddate;
	private Integer peoplenum;
	private Integer hotelid;
	private String hotelname;
	private String email;
	private String roomname;
	private Integer totalprice;
	private String customerRemark;
	private Boolean bedAdding;
	private Integer stayDayNum;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSearchname() {
		return searchname;
	}
	public void setSearchname(String searchname) {
		this.searchname = searchname;
	}
	public java.sql.Date getStartdate() {
		return startdate;
	}
	public void setStartdate(java.sql.Date startdate) {
		this.startdate = startdate;
	}
	public java.sql.Date getEnddate() {
		return enddate;
	}
	public void setEnddate(java.sql.Date enddate) {
		this.enddate = enddate;
	}
	public Integer getPeoplenum() {
		return peoplenum;
	}
	public void setPeoplenum(Integer peoplenum) {
		this.peoplenum = peoplenum;
	}
	public Integer getHotelid() {
		return hotelid;
	}
	public void setHotelid(Integer hotelid) {
		this.hotelid = hotelid;
	}
	public String getHotelname() {
		return hotelname;
	}
	public void setHotelname(String hotelname) {
		this.hotelname = hotelname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRoomname() {
		return roomname;
	}
	public void setRoomname(String roomname) {
		this.roomname = roomname;
	}
	public Integer getTotalprice() {
		return totalprice;
	}
	public void setTotalprice(Integer totalprice) {
		this.totalprice = totalprice;
	}
	public String getCustomerRemark() {
		return customerRemark;
	}
	public void setCustomerRemark(String customerRemark) {
		this.customerRemark = customerRemark;
	}
	public Boolean getBedAdding() {
		return bedAdding;
	}
	public void setBedAdding(Boolean bedAdding) {
		this.bedAdding = bedAdding;
	}
	public Integer getStayDayNum() {
		return stayDayNum;
	}
	public void setStayDayNum(Integer stayDayNum) {
		this.stayDayNum = stayDayNum;
	}
	
}
