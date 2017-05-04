package com.icastle.Orders.model;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Table;

public class OrdersVO implements java.io.Serializable{

	private Integer orderId;
	private java.sql.Timestamp orderedDate;
	private Integer memberId;
	private Integer roomId;
	private Integer hotelId;
	private String hotelName;
	private Integer roomTypeId;
	private String roomTypeName;
	private java.sql.Date checkinDay;
	private java.sql.Date checkoutDay;
	private Integer roomCount;
	private Integer peopleNum;
	private Boolean breakfast;
	private Boolean dinner;
	private Boolean afternoonTea;
	private Integer price;
	private String roomNo;
	private String reservationer;
	private java.sql.Date bdate;
	private String tel;
	private String email;
	private String addr;
	private String personId;
	private String country;
	private String passport;
	private Boolean bedAdding;
	private Integer pricePerPerson;
	private String customerRemark;
	private String hotelRemark;
	private String memo;
	private Boolean orderState;
	private java.sql.Timestamp cancelDate;
//	private Set<OrderFollowersVO> orderFollowersVO = new LinkedHashSet<OrderFollowersVO>();
	
	public Integer getOrderId() {
		return orderId;
	}
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	public java.sql.Timestamp getOrderedDate() {
		return orderedDate;
	}
	public void setOrderedDate(java.sql.Timestamp orderedDate) {
		this.orderedDate = orderedDate;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public Integer getMemberId() {
		return memberId;
	}
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}
	public Integer getRoomId() {
		return roomId;
	}
	public void setRoomId(Integer roomId) {
		this.roomId = roomId;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Boolean getOrderState() {
		return orderState;
	}
	public void setOrderState(Boolean orderState) {
		this.orderState = orderState;
	}
	public String getReservationer() {
		return reservationer;
	}
	public void setReservationer(String reservationer) {
		this.reservationer = reservationer;
	}
	public java.sql.Date getBdate() {
		return bdate;
	}
	public void setBdate(java.sql.Date bdate) {
		this.bdate = bdate;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getPassport() {
		return passport;
	}
	public void setPassport(String passport) {
		this.passport = passport;
	}
	public Boolean getBedAdding() {
		return bedAdding;
	}
	public void setBedAdding(Boolean bedAdding) {
		this.bedAdding = bedAdding;
	}
	public Integer getHotelId() {
		return hotelId;
	}
	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}
	public Integer getRoomTypeId() {
		return roomTypeId;
	}
	public void setRoomTypeId(Integer roomTypeId) {
		this.roomTypeId = roomTypeId;
	}
	public String getRoomTypeName() {
		return roomTypeName;
	}
	public void setRoomTypeName(String roomTypeName) {
		this.roomTypeName = roomTypeName;
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
	public Integer getRoomCount() {
		return roomCount;
	}
	public void setRoomCount(Integer roomCount) {
		this.roomCount = roomCount;
	}
	public Integer getPeopleNum() {
		return peopleNum;
	}
	public void setPeopleNum(Integer peopleNum) {
		this.peopleNum = peopleNum;
	}
	public Boolean getBreakfast() {
		return breakfast;
	}
	public void setBreakfast(Boolean breakfast) {
		this.breakfast = breakfast;
	}
	public Boolean getDinner() {
		return dinner;
	}
	public void setDinner(Boolean dinner) {
		this.dinner = dinner;
	}
	public Boolean getAfternoonTea() {
		return afternoonTea;
	}
	public void setAfternoonTea(Boolean afternoonTea) {
		this.afternoonTea = afternoonTea;
	}
	public String getRoomNo() {
		return roomNo;
	}
	public void setRoomNo(String roomNo) {
		this.roomNo = roomNo;
	}
	public Integer getPricePerPerson() {
		return pricePerPerson;
	}
	public void setPricePerPerson(Integer pricePerPerson) {
		this.pricePerPerson = pricePerPerson;
	}
	public String getCustomerRemark() {
		return customerRemark;
	}
	public void setCustomerRemark(String customerRemark) {
		this.customerRemark = customerRemark;
	}
	public String getHotelRemark() {
		return hotelRemark;
	}
	public void setHotelRemark(String hotelRemark) {
		this.hotelRemark = hotelRemark;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public java.sql.Timestamp getCancelDate() {
		return cancelDate;
	}
	public void setCancelDate(java.sql.Timestamp cancelDate) {
		this.cancelDate = cancelDate;
	}
//	public Set<OrderFollowersVO> getOrderFollowersVO() {
//		return orderFollowersVO;
//	}
//	public void setOrderFollowersVO(Set<OrderFollowersVO> orderFollowersVO) {
//		this.orderFollowersVO = orderFollowersVO;
//	}
	
}
