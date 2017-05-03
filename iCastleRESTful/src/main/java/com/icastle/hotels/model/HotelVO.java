package com.icastle.hotels.model;

import java.util.*;
import com.icastle.rooms.model.*;

public class HotelVO {

	private Integer hotelId;
	private String hotelName;
	private String email;
	private String pw;
	private String addr;
	private String zone;
	private Double point;
	private Integer hot;
	private Integer star;
	private Integer hotelState;
	private String registerId;
	
	private Set<RoomsVO> rooms = new HashSet<RoomsVO>(); 
	
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
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
	public Integer getStar() {
		return star;
	}
	public void setStar(Integer star) {
		this.star = star;
	}
	public Integer getHotelState() {
		return hotelState;
	}
	public void setHotelState(Integer hotelState) {
		this.hotelState = hotelState;
	}
	public String getRegisterId() {
		return registerId;
	}
	public void setRegisterId(String registerId) {
		this.registerId = registerId;
	}
	public Set<RoomsVO> getRooms() {
		return rooms;
	}
	public void setRooms(Set<RoomsVO> rooms) {
		this.rooms = rooms;
	}
}