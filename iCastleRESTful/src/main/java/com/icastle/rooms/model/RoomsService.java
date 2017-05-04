package com.icastle.rooms.model;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class RoomsService {
	RoomsDAO_interface dao = new RoomsHibernateDAO();
	
	//批次新增房間
	public Integer insertRooms(List<RoomsVO> roomList){
		return dao.insert(roomList);
	}
	
	//更新除了價錢以外的欄位
	public Integer updateDetail(List<RoomsVO> roomList){
		return dao.updateDetail(roomList);
	}
	
	//取得飯店整月的特定房型價錢
	public List<RoomsVO> getRoomsByMonth(Integer hotelId, Integer roomTypeId, Integer month){
		return dao.getRoomsByMonth(hotelId, roomTypeId, month);
	}
	
	//查詢該飯店所在期間內的空房型
	public List<RoomsVO> findRooms(Integer hotelId, Integer peopleNum, Date star, Date end){
		return dao.findRooms(hotelId, peopleNum, star, end);
	}
	
	//下訂單時修改bookedNum(以roomId及入住天數修改)
	public Integer getOrder(Integer roomId, Integer stayDayNum, Integer roomCount){
		return dao.getOrder(roomId, stayDayNum, roomCount);
	}
	
	//下訂單時修改bookedNum(以hotelId、roomTypeId、checkinDay、checkoutDay修改)
	public Integer getOrder(Integer hotelId, Integer roomTypeId, Date checkinDay, Date checkoutDay, Integer roomCount){
		return dao.getOrder(hotelId, roomTypeId, checkinDay, checkoutDay, roomCount);
	}
	
	//下訂單時修改bookedNum(自動判斷使用哪種getOrder)
	public Integer getOrderByAuto(Integer roomId, Integer hotelId, Integer roomTypeId, String checkinDay, String checkoutDay, Integer stayDayNum, Integer roomCount){
		String checkinMonth = checkinDay.substring(6, 8);
		String checkoutMonth = checkoutDay.substring(6, 8);
		Integer updateCount = 0;
		
		if(checkinMonth.equals(checkoutMonth)){
			updateCount = getOrder(roomId, stayDayNum, roomCount);
		}else{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			long start = 0;
			long end = 0;
			
			try {
				start = sdf.parse(checkinDay).getTime();
				end = sdf.parse(checkoutDay).getTime() - (1000*60*60*24);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			updateCount = getOrder(hotelId, roomTypeId, new Date(start), new Date(end), roomCount);
		}
		return updateCount;
	}
	
	//更新房價
	public Integer updatePrice(List<RoomsVO> roomsList){
		return dao.updatePrice(roomsList);
	}
	
	//計算入住天數
	public Integer getstayDayNum(String checkinDay, String checkoutDay){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int stayDayNum = 0;
		try {
			long start = sdf.parse(checkinDay).getTime();
			long end = sdf.parse(checkoutDay).getTime();
			stayDayNum = (int)((end - start) / (1000*60*60*24));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return stayDayNum;
	}
	
	//取得每日房價(以roomId及入住天數取得)
	public Map<String,Integer> getPerPrice(Integer roomId, Integer stayDayNum){
		return dao.getPerPrice(roomId, stayDayNum);
	}
	
	//得每日房價(以hotelId、roomTypeId、checkinDay、checkoutDay取得)
	public Map<String,Integer> getPerPrice(Integer hotelId, Integer roomTypeId, Date checkinDay, Date checkoutDay){
		return dao.getPerPrice(hotelId, roomTypeId, checkinDay, checkoutDay);
	}
	
	//得每日房價(自動判斷使用哪種getPerPrice)
	public Map<String,Integer> getPerPriceByAuto(Integer roomId, Integer hotelId, Integer roomTypeId, String checkinDay, String checkoutDay, Integer stayDayNum){
		String checkinMonth = checkinDay.substring(6, 8);
		String checkoutMonth = checkoutDay.substring(6, 8);
		Map<String,Integer> PerPrice = null;
		
		if(checkinMonth.equals(checkoutMonth)){
			PerPrice = getPerPrice(roomId, stayDayNum);
		}else{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			long start = 0;
			long end = 0;
			
			try {
				start = sdf.parse(checkinDay).getTime();
				end = sdf.parse(checkoutDay).getTime() - (1000*60*60*24);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			PerPrice = getPerPrice(hotelId, roomTypeId, new Date(start), new Date(end));
		}
		return PerPrice;
	}
	
	//計算總價
	public Integer getTotalPrice(Map<String,Integer> PerPrice){
		Collection<Integer> pprice = PerPrice.values();
		int count = 0;
		for(Integer price: pprice){
			count += price;
		}
		return count;
	}
	
	//REST查詢該飯店所在期間內的一筆房型
	public RoomsVO findRoom(Integer hotelId, Integer peopleNum, Date star, Date end, String roomName){
		return dao.findRoomForREST(hotelId, peopleNum, star, end, roomName);
	}
	
//	//REST查詢該飯店所在期間內的所有房型
//	public List<RoomsVO> findRoomsForREST(Integer hotelId, Integer peopleNum, Date star, Date end){
//		return dao.findRoomsForREST(hotelId, peopleNum, star, end);
//	}
}
