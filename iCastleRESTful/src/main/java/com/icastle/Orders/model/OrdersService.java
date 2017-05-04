package com.icastle.Orders.model;

import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import javax.xml.ws.RespectBinding;

public class OrdersService {
	
	private OrdersDAO_interface dao = null;
	
	
	public OrdersService(){
		dao = new OrdersHibernateDAO();
	}
	
//	LINE新增訂單
	public void lineOrder(String reservationer, String bdate, String tel, String bedadding){
		
	}
	
//	新增訂單
	public void newOrder(OrdersVO ordersVO){

		ordersVO.setOrderedDate(new java.sql.Timestamp(new GregorianCalendar().getInstance().getTimeInMillis()));

		dao.insert(ordersVO);
	}
	
//	客戶修改訂單狀態
	public void customerUpdate(Integer orderId, Boolean orderState){
		OrdersVO order = dao.select_by_orderId(orderId);
		
		java.sql.Timestamp day = new java.sql.Timestamp(new GregorianCalendar().getInstance().getTimeInMillis());
		
		order.setOrderState(orderState);
		order.setCancelDate(day);
		
		dao.update(order);
	}
	
//	客戶搜尋所有訂單
	public List<OrdersVO> search_By_MemberId(Integer memberId){
		List<OrdersVO> result = dao.select_by_memberId(memberId);
		
		return result;
	}
	
//	客戶和業者搜尋單筆訂單
	public OrdersVO search_By_OrderId(Integer orderId){
		OrdersVO result = dao.select_by_orderId(orderId);
		
		return result;
	}
	
//	業者修改訂單內容
	public void industryUpdate(Integer orderId, String memo){
		OrdersVO order = dao.select_by_orderId(orderId);
		
		order.setMemo(memo);
		
		dao.update(order);
	}

//	業者搜尋訂單
	public List<OrdersVO> search_By_HotelId(Integer hotelId, Integer roomTypeId, Integer year, Integer month, Integer day, Boolean state){
		
		if(day == null){
			if(state == null){
				if(roomTypeId == null){
					if(month == null){
						return dao.select_by_hotelId_year(hotelId, year);
					}else{
						return dao.select_by_hotelId_month(hotelId, year, month);
					}
				}else{
					if(month == null){
						return dao.select_by_hotelId_year_roomTypeId(hotelId, roomTypeId, year);
					}else{
						return dao.select_by_hotelId_month_roomTypeId(hotelId, roomTypeId, year, month);
					}
				}
			}else{
				if(roomTypeId == null){
					if(month == null){
						return dao.select_by_hotelId_year_orderstate(hotelId, year, state);
					}else{
						return dao.select_by_hotelId_month_orderstate(hotelId, year, month, state);
					}
				}else{
					if(month == null){
						return dao.select_by_hotelId_year_roomTypeId_orderstate(hotelId, roomTypeId, year, state);
					}else{
						return dao.select_by_hotelId_month_roomTypeId_orderstate(hotelId, roomTypeId, year, month, state);
					}
				}
			}
		}else{
			if(state == null){
				if(roomTypeId == null){
					return dao.select_by_hotelId_day(hotelId, year, month, day);
				}else{
					return dao.select_by_hotelId_day_roomTypeId(hotelId, roomTypeId, year, month, day);
				}
			}else{
				if(roomTypeId == null){
					return dao.select_by_hotelId_day_orderstate(hotelId, year, month, day, state);
				}else{
					return dao.select_by_hotelId_day_roomTypeId_orderstate(hotelId, roomTypeId, year, month, day, state);
				}
			}
		}
		
//		if(month == null && day == null && roomTypeId == null && state == null){
//			return dao.select_by_hotelId_year(hotelId, year);
//		}
//		if(day == null && roomTypeId == null && state == null){
//			return dao.select_by_hotelId_month(hotelId, year, month);
//		}
//		if(roomTypeId == null && state == null){
//			return dao.select_by_hotelId_day(hotelId, year, month, day);
//		}
//		if(month == null && day == null && roomTypeId == null){
//			return dao.select_by_hotelId_year_orderstate(hotelId, year, state);
//		}
//		if(day == null && roomTypeId == null){
//			return dao.select_by_hotelId_month_orderstate(hotelId, year, month, state);
//		}
//		if(roomTypeId == null){
//			return dao.select_by_hotelId_day_orderstate(hotelId, year, month, day, state);
//		}
//		if(month == null && day == null && state == null){
//			return dao.select_by_hotelId_year_roomTypeId(hotelId, roomTypeId, year);
//		}
//		if(day == null && state == null){
//			return dao.select_by_hotelId_month_roomTypeId(hotelId, roomTypeId, year, month);
//		}
//		if(state == null){
//			return dao.select_by_hotelId_day_roomTypeId(hotelId, roomTypeId, year, month, day);
//		}
//		if(month == null && day == null){
//			return dao.select_by_hotelId_year_roomTypeId_orderstate(hotelId, roomTypeId, year, state);
//		}
//		if(day == null){
//			return dao.select_by_hotelId_month_roomTypeId_orderstate(hotelId, roomTypeId, year, month, state);
//		}
//		if(month != null && day != null && roomTypeId != null && state != null){
//			return dao.select_by_hotelId_day_roomTypeId_orderstate(hotelId, roomTypeId, year, month, day, state);
//		}else{
//			return null;
//		}
	}
	
//	搜尋所有訂單
	public List<OrdersVO> searchAll(){
		return dao.select_all();
	}
	
}
