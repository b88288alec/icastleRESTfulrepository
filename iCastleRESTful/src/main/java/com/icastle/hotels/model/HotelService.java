package com.icastle.hotels.model;

import java.sql.Date;
import java.util.List;
import java.util.Random;

public class HotelService {
	
	HotelDAO_Interface dao = null;
	
	public HotelService (){
//		dao = new HotelDAOJNDI();
		dao = new HotelDAOHibernate();
	}
	
	//新增飯店
	public HotelVO addHotel(HotelVO hotelVO){
		return dao.addHotel(hotelVO);
	}
	
	//修改密碼，登入之後輸入舊密碼和新密碼以修改密碼
	public boolean changePw(Integer hotelId, String oldpw, String newpw){
		HotelVO hotelvo = dao.findByPrimaryKey(hotelId);
		if (hotelvo.getPw().equals(oldpw.trim())){
			hotelvo.setPw(newpw);
			dao.update(hotelvo);
			return true;
		}else
			return false;
	}
	
	//忘記密碼時系統會產生隨機數作為新的密碼
	public String createPw(Integer hotelId){
		HotelVO hotelvo = dao.findByPrimaryKey(hotelId);
		Random ran = new Random();
		int range = 999999;
		String newpw = String.valueOf(ran.nextInt(range));
		hotelvo.setPw(newpw);
		dao.update(hotelvo);
		return newpw;
	}
	
	//修改飯店狀態(管理員同意上架或將飯店下架)
	public Integer updateState(Integer hotelId, Integer state) {
		HotelVO hotelvo = dao.findByPrimaryKey(hotelId);
		hotelvo.setHotelState(state);
		dao.update(hotelvo);
		return state;
	}
	
	//修改飯店評分(當有客人給了新評分時)
	public Double updatePoint(int hotelId, Double point) {
		HotelVO hotelvo = dao.findByPrimaryKey(hotelId);
		hotelvo.setPoint(point);
		dao.update(hotelvo);
		return point;
	}
	
	//由管理員更新飯店的所有欄位
	public HotelVO adminUpdate(HotelVO hotelVO){
		dao.update(hotelVO);
		return hotelVO;
	}

	//根據飯店ID找到一家飯店
	public HotelVO findByPrimaryKey(Integer hotelId) {
		return dao.findByPrimaryKey(hotelId);
	}
	
	//飯店登入，如果登入失敗則回傳null
	public HotelVO checkAccountPw(String email, String pw){
		return dao.checkAccountPw(email, pw);
	}
	
	//首頁查詢，根據關鍵字(飯店名稱or地區的模糊搜尋)、入住日、退房日、人數尋找符合條件的飯店，並將資料封裝成ListVO物件
	public List<ListVO> indexQuery(String type, Date startDate, Date endDate, Integer peopleNum) {
		return dao.indexQuery(type, startDate, endDate, peopleNum);
	}
	
	/*進階查詢，條件包含首頁查詢，外加排序(熱門度or最低價格or星級排行)
	 *	                                            、最低價
	 *                      、最高價
	 *                      、評分(0~5.0)
	 *                      、星等(0~5)
	 */
	public List<ListVO> advancedQuery(String zone, Date startDate, Date endDate
			, Integer peopleNum, String order, Integer lowprice, Integer highprice, double point, Integer star) {
		return dao.advancedQuery(zone, startDate, endDate, peopleNum, order, lowprice, highprice, point, star);
	}
	
	//查詢全部的飯店
	public List<HotelVO> getAll() {
		return dao.getAll();
	}
	
	//查詢飯店ID
	public Integer getHotelId(String hotelName, String zone){
		List<Integer> hotelId = dao.getId(hotelName, zone);
		Integer result = hotelId.get(0);
		
		return result;
	}
}
