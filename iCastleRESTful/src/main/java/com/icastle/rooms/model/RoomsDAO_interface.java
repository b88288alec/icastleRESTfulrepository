package com.icastle.rooms.model;

import java.sql.Date;
import java.util.List;
import java.util.Map;

public interface RoomsDAO_interface {
	//批次新增房間
	public Integer insert(List<RoomsVO> roomList);
	
	//更新除了價錢以外的欄位  目前暫定不需要的方法，所以先@Deprecated
	@Deprecated
	public Integer updateDetail(List<RoomsVO> roomList);
	
	//取得飯店整月的特定房型價錢
	public List<RoomsVO> getRoomsByMonth(Integer hotelId, Integer roomTypeId, Integer month);
	
	//查詢該飯店所在期間內的空房型
	public List<RoomsVO> findRooms(Integer hotelId, Integer peopleNum, Date star, Date end);
	
	//下訂單時修改bookedNum(以roomId及入住天數修改) 會有入住\退房日期跨月抓錯資料的問題，但效能較好 **overloading**
	public Integer getOrder(Integer roomId, Integer dayNum, Integer roomCount);
	
	//下訂單時修改bookedNum(以hotelId、roomTypeId、checkinDay、checkoutDay修改) **overloading**
	public Integer getOrder(Integer hotelId, Integer roomTypeId, Date checkinDay, Date checkoutDay, Integer roomCount);
	
	//更新房價
	public Integer updatePrice(List<RoomsVO> roomsList);
	
	//取得每日房價(以roomId及入住天數取得) 會有入住\退房日期跨月抓錯資料的問題，但效能較好 **overloading**
	public Map<String,Integer> getPerPrice(Integer roomId, Integer stayDayNum);
	
	//得每日房價(以hotelId、roomTypeId、checkinDay、checkoutDay取得) **overloading**
	public Map<String,Integer> getPerPrice(Integer hotelId, Integer roomTypeId, Date checkinDay, Date checkoutDay);
	
	//REST查詢該飯店所在期間內的一筆房型
	public RoomsVO findRoom(Integer hotelId, Integer peopleNum, Date star, Date end, String roomName);
}
