package com.icastle.facade;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.icastle.Orders.model.OrdersService;
import com.icastle.Orders.model.OrdersVO;
import com.icastle.hotels.model.HotelService;
import com.icastle.hotels.model.ListVO;
import com.icastle.members.model.MembersService;
import com.icastle.members.model.MembersVO;
import com.icastle.restful.model.RESTfulService;
import com.icastle.restful.model.RESTfulVO;
import com.icastle.rooms.model.RoomsService;
import com.icastle.rooms.model.RoomsVO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/iCastle")
public class HotelFacade {

//	搜尋所有飯店
	@RequestMapping(value="/hotelList", method={RequestMethod.GET, RequestMethod.POST}, produces={"application/json"})
	public Map<String, Object> search(@RequestParam("type")String type, @RequestParam("starDate")String startDate, @RequestParam("endDate")String endDate, @RequestParam("peopleNum")Integer peopleNum, HttpServletRequest request, HttpServletResponse response){
		
//		傳錯誤訊息給前端
		try{
			
//		處理參數
			HotelService hs = new HotelService();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			java.sql.Date start = null;
			java.sql.Date end = null;
			try {
				start = new java.sql.Date(sdf.parse(startDate).getTime());
				end = new java.sql.Date(sdf.parse(endDate).getTime());
			} catch (ParseException e) {
				Map<String, Object> error = new HashMap<>();
				error.put("errorMsg", e.getMessage());
				return error;
			}
			
//		放入session備用--無法使用
//		HttpSession session = request.getSession();
//		session.setAttribute("type", type);
//		session.setAttribute("starDate", start);
//		session.setAttribute("endDate", end);
//		session.setAttribute("peopleNum", peopleNum);
			
//		產生亂數id
			RESTfulService rests = new RESTfulService();
			Integer id = null;
			do{
				id = Double.valueOf(Math.random()*99999999.0).intValue();
			}while(!rests.search(id));
			
//		放入資料庫備用
			RESTfulVO db = new RESTfulVO();
			db.setId(id);
			db.setSearchname(type);
			db.setStartdate(start);
			db.setEnddate(end);
			db.setPeoplenum(peopleNum);
			rests.insert(db);
			
//		查出多筆飯店
			List<ListVO> result = hs.indexQuery(type, start, end, peopleNum);
			
//		包成一包JSON
			Map<String, Object> mapresult = new HashMap<>();
			mapresult.put("checkinDay", start);
			mapresult.put("checkoutDay", end);
			mapresult.put("hotelsList", result);
			mapresult.put("peopleNum", peopleNum);
			mapresult.put("saveId", id);
			
			return mapresult;
			
		}catch(Exception e){
			Map<String, Object> error = new HashMap<>();
			error.put("errorMsg", e.getMessage());
			return error;
		}
	}
	
//	選取單筆飯店
	@RequestMapping(value="/roomlist", method={RequestMethod.GET, RequestMethod.POST}, produces={"application/json"})
	public Map<String, Object> select(@RequestParam("hotelName")String hotelName, @RequestParam("saveId")Integer saveId, HttpServletRequest request, HttpServletResponse response){

//		傳錯誤訊息給前端
		try{
			
//			從session取出參數--無法使用
//			HttpSession session = request.getSession();
//			String zone = (String)session.getAttribute("type");
//			java.sql.Date star = (java.sql.Date)session.getAttribute("starDate");
//			java.sql.Date end = (java.sql.Date)session.getAttribute("endDate");
//			Integer peopleNum = (Integer)session.getAttribute("peopleNum");
			
//			從資料庫取出參數
			RESTfulService rests = new RESTfulService();
			RESTfulVO restVO = rests.select(saveId);
			String zone = restVO.getSearchname();
			java.sql.Date star = restVO.getStartdate();
			java.sql.Date end = restVO.getEnddate();
			Integer peopleNum = restVO.getPeoplenum();
			
//			取出hotelID
			HotelService hs = new HotelService();
			List<Object[]> nId = hs.getHotelId(hotelName, zone);
			Integer hotelId = null;
			String hotelFullName = null;
			for(Object[] obj: nId){
				hotelId = (Integer)obj[0];
				hotelFullName = (String)obj[1];
			}
			
//			放入session備用--無法使用
//			session.setAttribute("hotelId", hotelId);
//			session.setAttribute("hotelName", hotelFullName);
			
//			存入資料庫備用
			restVO.setHotelid(hotelId);
			restVO.setHotelname(hotelFullName);
			rests.update(restVO);
			
//			查出一間飯店的多筆房間資訊
			RoomsService rs = new RoomsService();
			List<RoomsVO> result = rs.findRooms(hotelId, peopleNum, star, end);
			
//			包成一包JSON
			Map<String, Object> mapresult = new HashMap<>();
			mapresult.put("checkinDay", star);
			mapresult.put("checkoutDay", end);
			mapresult.put("hotelName", hotelFullName);
			mapresult.put("roomsList", result);
			mapresult.put("peopleNum", peopleNum);
			mapresult.put("saveId", saveId);
			
			return mapresult;
			
		}catch(Exception e){
			Map<String, Object> error = new HashMap<>();
			error.put("errorMsg", e.getMessage());
			return error;
		}
	}
	
//	確認預訂畫面
	@RequestMapping(value="/check", method={RequestMethod.GET, RequestMethod.POST}, produces={"application/json"})
	public Map<String, Object> checkOrder(@RequestParam("roomName")String roomName, @RequestParam("bedAdding")Boolean bedAdding, @RequestParam("customerRemark")String customerRemark, @RequestParam("email")String email, @RequestParam("saveId")Integer saveId, HttpServletRequest request, HttpServletResponse response){

//		傳錯誤訊息給前端
		try{
//			取出存放在session內的各種資料--無法使用
//			HttpSession session = request.getSession();
//			java.sql.Date star = (java.sql.Date)session.getAttribute("starDate");
//			java.sql.Date end = (java.sql.Date)session.getAttribute("endDate");
//			Integer peopleNum = (Integer)session.getAttribute("peopleNum");
//			Integer hotelId = (Integer)session.getAttribute("hotelId");
//			String hotelName = (String)session.getAttribute("hotelName");
			
//			取出資料庫的參數
			RESTfulService rests = new RESTfulService();
			RESTfulVO restVO = rests.select(saveId);
			java.sql.Date star = restVO.getStartdate();
			java.sql.Date end = restVO.getEnddate();
			Integer peopleNum = restVO.getPeoplenum();
			Integer hotelId = restVO.getHotelid();
			String hotelName = restVO.getHotelname();
			
//			取出房型資料
			RoomsService rs = new RoomsService();
			RoomsVO roomsVO = rs.findRoom(hotelId, peopleNum, star, end, roomName);
			Integer stayDayNum = rs.getstayDayNum(star.toString(), end.toString());
			Map<String,Integer> PerPrice = rs.getPerPriceByAuto(roomsVO.getRoomId(), roomsVO.getHotelId(), roomsVO.getRoomTypeId(), star.toString(), end.toString(), stayDayNum);
			int totalPrice = rs.getTotalPrice(PerPrice);
			if(bedAdding){
				totalPrice = totalPrice + roomsVO.getPricePerPerson()*stayDayNum;
			}
			
//			取出會員下訂資料
			MembersService ms = new MembersService();
			List<MembersVO> members = ms.findByPrimaryKey(email);
			MembersVO membersVO = members.get(0);
			
//			把房型與預定資料存入CheckVO
			CheckVO checkVO = new CheckVO();
			checkVO.setMembersVO(membersVO);
			checkVO.setRoomsVO(roomsVO);
			checkVO.setPerPrice(PerPrice);
			checkVO.setTotalPrice(totalPrice);
			checkVO.setBedAdding(bedAdding);
			checkVO.setCustomerRemark(customerRemark);
			checkVO.setCheckinDay(star);
			checkVO.setCheckoutDay(end);
			checkVO.setHotelName(hotelName);
			
//			存入資料庫備用
			restVO.setEmail(email);
			restVO.setRoomname(roomsVO.getRoomTypeName());
			restVO.setTotalprice(totalPrice);
			restVO.setCustomerRemark(customerRemark);
			restVO.setBedAdding(bedAdding);
			restVO.setStayDayNum(stayDayNum);
			rests.update(restVO);
			
//			存入session--無法使用
//			session.setAttribute("CheckVO", checkVO);
//			session.setAttribute("stayDayNum", stayDayNum);
			
//			存入MAP
			Map<String, Object> success = new HashMap<>();
			success.put("success", checkVO);
			success.put("saveId", saveId);
			
			return success;
		}catch(Exception e){
			Map<String, Object> error = new HashMap<>();
			error.put("errorMsg", e.getMessage());
			return error;
		}
	}
	
//	下訂
	@RequestMapping(value="/order", method={RequestMethod.GET, RequestMethod.POST}, produces={"application/json"})
	public Map<String, Object> order(@RequestParam("saveId")Integer saveId, HttpServletRequest request, HttpServletResponse response){
		try{
			
//			取出存在session的內容--無法使用
//			HttpSession session = request.getSession();
//			CheckVO checkVO = (CheckVO)session.getAttribute("CheckVO");
//			RoomsVO room = checkVO.getRoomsVO();
//			MembersVO member = checkVO.getMembersVO();
//			Integer totalPrice = checkVO.getTotalPrice();
			
//			取出資料庫參數
			RESTfulService rests = new RESTfulService();
			RESTfulVO restVO = rests.select(saveId);
			java.sql.Date star = restVO.getStartdate();
			java.sql.Date end = restVO.getEnddate();
			Integer peopleNum = restVO.getPeoplenum();
			Integer hotelId = restVO.getHotelid();
			String hotelName = restVO.getHotelname();
			String roomName = restVO.getRoomname();
			String email = restVO.getEmail();
			Integer totalPrice = restVO.getTotalprice();
			Boolean bedAdding = restVO.getBedAdding();
			String customerRemark = restVO.getCustomerRemark();
			Integer stayDayNum = restVO.getStayDayNum();
			
//			取出房間資訊
			RoomsService rs = new RoomsService();
			RoomsVO room = rs.findRoom(hotelId, peopleNum, star, end, roomName);
			
//			取出會員訂購資訊
			MembersService ms = new MembersService();
			List<MembersVO> members = ms.findByPrimaryKey(email);
			MembersVO member = members.get(0);
			
//			把訂單資料輸入OrdersVO
			OrdersVO order = new OrdersVO();
			
			order.setMemberId(member.getMemberId());
			order.setRoomId(room.getRoomId());
			order.setHotelId(room.getHotelId());
			order.setHotelName(hotelName);
			order.setRoomTypeId(room.getRoomTypeId());
			order.setRoomTypeName(room.getRoomTypeName());
			order.setCheckinDay(star);
			order.setCheckoutDay(end);
			order.setRoomCount(1);
			order.setPeopleNum(peopleNum);
			order.setBreakfast(room.isBreakfast());
			order.setDinner(room.isDinner());
			order.setAfternoonTea(room.isAfternoonTea());
			order.setPrice(totalPrice);
			order.setReservationer(member.getName());
			order.setBdate(member.getBdate());
			order.setTel(member.getTel());
			order.setEmail(member.getEmail());
			order.setAddr(member.getAddr());
			order.setPersonId(member.getPersonId());
			order.setCountry(member.getCountry());
			order.setPassport(member.getPassport());
			order.setBedAdding(bedAdding);
			order.setPricePerPerson(room.getPricePerPerson());
			order.setCustomerRemark(customerRemark);
			order.setHotelRemark(room.getRemark());
			order.setOrderState(true);
			
//			送到資料庫
			rs.getOrderByAuto(room.getRoomId(), room.getHotelId(), room.getRoomTypeId(), star.toString(), end.toString(), stayDayNum, 1);
			OrdersService os = new OrdersService();
			os.newOrder(order);
			
//			刪除session--無法使用
//			Enumeration en = session.getAttributeNames();
//			while(en.hasMoreElements()){
//				String name = (String)en.nextElement();
//				session.removeAttribute(name);
//			}
			
//			刪除資料庫參數
			rests.delete(saveId);
			
//			存入MAP
			Map<String, Object> success = new HashMap<>();
			success.put("success", "下訂成功");
			
//			傳回是否成功
			return success;
		}catch(Exception e){
			Map<String, Object> error = new HashMap<>();
			error.put("errorMsg", e.getMessage());
			return error;
		}
		
	}
	
//	測試用
//	@RequestMapping(method=RequestMethod.GET, produces={"application/json", "application/xml"})
	public List<OrdersVO> searchAll(){
		OrdersService os = new OrdersService();
		List<OrdersVO> result = os.searchAll();
		
		return result;
	}
	
//	測試用
//	@RequestMapping(method=RequestMethod.GET, produces={"application/json", "application/xml"})
	public OrdersVO searchById(@RequestParam(value="orderId")Integer orderId){
		OrdersService os = new OrdersService();
		OrdersVO result = os.search_By_OrderId(orderId);
		
		return result;
		
	}
	
}
