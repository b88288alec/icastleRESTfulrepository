package com.icastle.facade;

import java.io.Serializable;

import com.icastle.members.model.MembersVO;
import com.icastle.rooms.model.RoomsVO;

public class CheckVO implements Serializable{

	private RoomsVO roomsVO;
	private MembersVO membersVO;
	
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
	
}
