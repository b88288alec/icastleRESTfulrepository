package com.icastle.facade;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

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
import com.icastle.rooms.model.RoomsService;
import com.icastle.rooms.model.RoomsVO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/iCastle")
public class HotelFacade {

//	搜尋所有飯店
	@RequestMapping(value="/hotelList", method=RequestMethod.GET, produces={"application/json", "application/xml"})
	public List<ListVO> search(@RequestParam("type")String type, @RequestParam("starDate")String startDate, @RequestParam("endDate")String endDate, @RequestParam("peopleNum")Integer peopleNum, HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		
//		處理參數
		HotelService hs = new HotelService();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		java.sql.Date start = null;
		java.sql.Date end = null;
		try {
			start = new java.sql.Date(sdf.parse(startDate).getTime());
			end = new java.sql.Date(sdf.parse(endDate).getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
//		放入session備用
		session.setAttribute("type", type);
		session.setAttribute("starDate", start);
		session.setAttribute("endDate", end);
		session.setAttribute("peopleNum", peopleNum);
		
//		查出多筆飯店
		List<ListVO> result = hs.indexQuery(type, start, end, peopleNum);
		
		return result;
	}
	
//	選取單筆飯店
	@RequestMapping(value="/roomlist", method=RequestMethod.GET, produces={"application/json", "application/xml"})
	public List<RoomsVO> select(@RequestParam("hotelName")String hotelName, HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		
//		從session取出參數
		String zone = (String)session.getAttribute("type");
		java.sql.Date star = (java.sql.Date)session.getAttribute("starDate");
		java.sql.Date end = (java.sql.Date)session.getAttribute("endDate");
		Integer peopleNum = (Integer)session.getAttribute("peopleNum");
		
//		取出hotelID
		HotelService hs = new HotelService();
		Integer hotelId = hs.getHotelId(hotelName, zone);
		
//		放入session備用
		session.setAttribute("hotelId", hotelId);
		
//		查出一間飯店的多筆房間資訊
		RoomsService rs = new RoomsService();
		List<RoomsVO> result = rs.findRooms(hotelId, peopleNum, star, end);
		
		return result;
	}
	
//	確認預訂畫面
	@RequestMapping(value="/check", method=RequestMethod.GET, produces={"application/json", "application/xml"})
	public CheckVO checkOrder(@RequestParam("roomName")String roomName, @RequestParam("email")String email, HttpServletRequest request, HttpServletResponse response){
		HttpSession session = request.getSession();
		
//		取出存放在session內的各種資料
		java.sql.Date star = (java.sql.Date)session.getAttribute("starDate");
		java.sql.Date end = (java.sql.Date)session.getAttribute("endDate");
		Integer peopleNum = (Integer)session.getAttribute("peopleNum");
		Integer hotelId = (Integer)session.getAttribute("hotelId");
		
//		取出房型資料
		RoomsService rs = new RoomsService();
		RoomsVO roomsVO = rs.findRoom(hotelId, peopleNum, star, end, roomName);
		
//		取出會員下訂資料
		MembersService ms = new MembersService();
		List<MembersVO> members = ms.findByPrimaryKey(email);
		MembersVO membersVO = members.get(0);
		
//		把房型與預定資料存入CheckVO
		CheckVO checkVO = new CheckVO();
		checkVO.setMembersVO(membersVO);
		checkVO.setRoomsVO(roomsVO);
		
//		存入session
		session.setAttribute("CheckVO", checkVO);
		
		return checkVO;
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
