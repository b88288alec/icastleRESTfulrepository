package com.icastle.facade;

import java.io.Serializable;
import java.util.Map;

import com.icastle.members.model.MembersVO;
import com.icastle.rooms.model.RoomsVO;

public class CheckVO implements Serializable{

	private RoomsVO roomsVO;
	private MembersVO membersVO;
	private Map<String,Integer> perPrice;
	private Integer totalPrice;
	private String customerRemark;
	private Boolean bedAdding;
	private java.sql.Date checkinDay;
	private java.sql.Date checkoutDay;
	private String hotelName;
	
	public RoomsVO getRoomsVO() {
		return roomsVO;
	}
	public void setRoomsVO(RoomsVO roomsVO) {
		this.roomsVO = roomsVO;
	}
	public MembersVO getMembersVO() {
		return membersVO;
	}
	public void setMembersVO(MembersVO membersVO) {
		this.membersVO = membersVO;
	}
	public Map<String, Integer> getPerPrice() {
		return perPrice;
	}
	public void setPerPrice(Map<String, Integer> perPrice) {
		this.perPrice = perPrice;
	}
	public Integer getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
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
	public java.sql.Date getCheckinDay() {
		return checkinDay;
	}
	public void setCheckinDay(java.sql.Date checkinDay) {
		this.checkinDay = checkinDay;
	}
	public java.sql.Date getCheckoutDay() {
		return checkoutDay;
	}
	public void setCheckoutDay(java.sql.Date checkoutDay) {
		this.checkoutDay = checkoutDay;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	
}
