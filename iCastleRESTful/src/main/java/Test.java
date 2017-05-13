import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

import com.icastle.hotels.model.HotelDAOHibernate;
import com.icastle.hotels.model.HotelDAO_Interface;
import com.icastle.members.model.MembersDAO_interface;
import com.icastle.members.model.MembersHibernateDAO;
import com.icastle.members.model.MembersVO;
import com.icastle.restful.model.RESTfulHibernateDAO;
import com.icastle.restful.model.RESTfulVO;
import com.icastle.restful.model.RESTful_interface;
import com.icastle.rooms.model.RoomsDAO_interface;
import com.icastle.rooms.model.RoomsHibernateDAO;
import com.icastle.rooms.model.RoomsService;
import com.icastle.rooms.model.RoomsVO;

public class Test {

//	public static void main(String[] args) {

//		測試搜尋客戶資料
//		MembersDAO_interface mh = new MembersHibernateDAO();
//		
//		List<MembersVO> members = mh.findByPrimaryKey("Sally@gmail.com");
//		
//		for(MembersVO result: members){
//			System.out.println(result.getAddr());
//			System.out.println(result.getCountry());
//			System.out.println(result.getEmail());
//			System.out.println(result.getGender());
//			System.out.println(result.getName());
//			System.out.println(result.getPassport());
//			System.out.println(result.getPersonId());
//			System.out.println(result.getPw());
//			System.out.println(result.getTel());
//			System.out.println(result.getMemberId());
//			System.out.println(result.getBdate());
//		}
		
//		測試搜尋ID&飯店名
//		HotelDAO_Interface hi = new HotelDAOHibernate();
//		
//		List hotelId = hi.getId("德立莊酒店", "台北");
//		System.out.println(hotelId.toString());
//		System.out.println((Object[])hotelId.get(0));
//		Object[] obj = (Object[])hotelId.get(0);
//		System.out.println(obj[0]);
//		System.out.println(obj[1]);
		
//		測試搜尋單筆房型
//		RoomsDAO_interface ri = new RoomsHibernateDAO();
//		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//		java.sql.Date start = null;
//		java.sql.Date end = null;
//		try {
//			start = new java.sql.Date(sdf.parse("2017/6/2").getTime());
//			end = new java.sql.Date(sdf.parse("2017/6/4").getTime());
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		
//		RoomsVO room = ri.findRoom(1, 4, start, end, "雅緻");
//		
//		System.out.println(room);
		
//		測試check
		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
//		java.sql.Date start = null;
//		java.sql.Date end = null;
//		try {
//			start = new java.sql.Date(sdf.parse("2017/6/2").getTime());
//			end = new java.sql.Date(sdf.parse("2017/6/4").getTime());
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		
//		RoomsService rs = new RoomsService();
//		RoomsVO roomsVO = rs.findRoom(1, 4, start, end, "雅緻");
//		System.out.println(start.toString());
//		Integer stayDayNum = rs.getstayDayNum(start.toString(), end.toString());
//		Map<String,Integer> PerPrice = rs.getPerPriceByAuto(roomsVO.getRoomId(), roomsVO.getHotelId(), roomsVO.getRoomTypeId(), start.toString(), end.toString(), stayDayNum);
//		for(String key: PerPrice.keySet()){
//			System.out.println(key);
//		}
//		for(Integer value: PerPrice.values()){
//			System.out.println(value);
//		}
//		int totalPrice = rs.getTotalPrice(PerPrice);
//		System.out.println(totalPrice);
//		roomsVO.setPrice(totalPrice);
//		
		
//		測試RESTful的table
//		RESTful_interface dao = new RESTfulHibernateDAO();
//		RESTfulVO restVO = new RESTfulVO();
//		restVO.setId(3345678);
//		dao.insert(restVO);
		
//		restVO.setBedAdding(true);
//		dao.update(restVO);
		
//		System.out.println(dao.search(3345678));
		
//		dao.delete(3345678);
//		
//	}

}
