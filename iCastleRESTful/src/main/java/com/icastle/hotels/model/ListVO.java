package com.icastle.hotels.model;

public class ListVO {
	private Integer hotelId;
	private String hotelName;
	private String zone;
	private Integer price;
	private Integer star;
	private Double point;
	private Integer hot;
	private boolean breakfast;
	private boolean dinner;
	private boolean roomWifi;
	
	public Integer getHotelId() {
		return hotelId;
	}
	public void setHotelId(Integer hotelId) {
		this.hotelId = hotelId;
	}
	public String getHotelName() {
		return hotelName;
	}
	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getStar() {
		return star;
	}
	public void setStar(Integer star) {
		this.star = star;
	}
	public Double getPoint() {
		return point;
	}
	public void setPoint(Double point) {
		this.point = point;
	}
	public Integer getHot() {
		return hot;
	}
	public void setHot(Integer hot) {
		this.hot = hot;
	}
	public boolean isBreakfast() {
		return breakfast;
	}
	public void setBreakfast(boolean breakfast) {
		this.breakfast = breakfast;
	}
	public boolean isDinner() {
		return dinner;
	}
	public void setDinner(boolean dinner) {
		this.dinner = dinner;
	}
	public boolean isRoomWifi() {
		return roomWifi;
	}
	public void setRoomWifi(boolean roomWifi) {
		this.roomWifi = roomWifi;
	}
	
}
