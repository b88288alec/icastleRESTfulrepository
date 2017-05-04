package com.icastle.rooms.model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.hibernate.Query;
import org.hibernate.Session;

import hibernate.util.HibernateUtil;

public class RoomsHibernateDAO implements RoomsDAO_interface {

	@Override
	public Integer insert(List<RoomsVO> roomList) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			for(RoomsVO vo : roomList){
				session.saveOrUpdate(vo);
			}
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		return null;
	}

	@Override
	@Deprecated
	public Integer updateDetail(List<RoomsVO> roomList) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Integer updateCount = 0;
		try{
			session.beginTransaction();
			Query query = session.createQuery("update RoomsVO set RoomTypeName = ?, roomNumber = ?, breakfast = ?, dinner = ?, afternoonTea = ?, bedAddable = ?, pricePerPerson = ?, remark = ? where roomId = ?");
			for(RoomsVO vo : roomList){
				query.setParameter(0, vo.getRoomTypeName());
				query.setParameter(1, vo.getRoomNumber());
				query.setParameter(2, vo.isBreakfast());
				query.setParameter(3, vo.isDinner());
				query.setParameter(4, vo.isAfternoonTea());
				query.setParameter(5, vo.isBedAddable());
				query.setParameter(6, vo.getPricePerPerson());
				query.setParameter(7, vo.getRemark());
				query.setParameter(8, vo.getRoomId());
				Integer count = query.executeUpdate();
				updateCount += count;
			}
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		return updateCount;
	}

	@Override
	public List<RoomsVO> getRoomsByMonth(Integer hotelId, Integer roomTypeId, Integer month) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<RoomsVO> list = null;
		try{
			session.beginTransaction();
			Query query = session.createQuery("from RoomsVO where hotelId = ? and roomTypeId = ? and MONTH(roomDate) = ?");
			query.setParameter(0, hotelId);
			query.setParameter(1, roomTypeId);
			query.setParameter(2, month);
			list = query.list();
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		return list;
	}

	@Override
	public List<RoomsVO> findRooms(Integer hotelId, Integer peopleNum, Date star, Date end) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<RoomsVO> list = new ArrayList<RoomsVO>();
		try{
			session.beginTransaction();
			Query query = session.createSQLQuery("{call indexQueryGetRoom(?,?,?,?)}");
			query.setParameter(0, hotelId);
			query.setParameter(1, star);
			query.setParameter(2, end);
			query.setParameter(3, peopleNum);
			List<Object[]> listObj = query.list();
			for(Object[] arrayObj : listObj){
				RoomsVO vo = new RoomsVO();
				vo.setRoomId((Integer)arrayObj[0]);
				vo.setRoomTypeId((Integer)arrayObj[1]);
				vo.setHotelId((Integer)arrayObj[2]);
				vo.setRoomDate((Date)arrayObj[3]);
				vo.setRoomTypeName((String)arrayObj[4]);
				vo.setPeopleNum((Integer)arrayObj[5]);
				vo.setBookedNum((Integer)arrayObj[6]);
				vo.setRoomNumber((Integer)arrayObj[7]);
				vo.setPrice((Integer)arrayObj[8]);
				vo.setBreakfast((Boolean)arrayObj[9]);
				vo.setDinner((Boolean)arrayObj[10]);
				vo.setAfternoonTea((Boolean)arrayObj[11]);
				vo.setBedAddable((Boolean)arrayObj[12]);
				vo.setPricePerPerson((Integer)arrayObj[13]);
				vo.setRemark((String)arrayObj[14]);
				list.add(vo);
			}
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		return list;
	}

	@Override
	public Integer getOrder(Integer roomId, Integer stayDayNum, Integer roomCount) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Integer updateCount = 0;
		try{
			session.beginTransaction();
			Query query = session.createQuery("update RoomsVO set bookedNum = bookedNum+? where roomId between ? and ?");
			Integer roomIdEnd = roomId + (stayDayNum - 1);
			query.setParameter(0, roomCount);
			query.setParameter(1, roomId);
			query.setParameter(2, roomIdEnd);
			updateCount = query.executeUpdate();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		return updateCount;
	}

	@Override
	public Integer getOrder(Integer hotelId, Integer roomTypeId, Date checkinDay, Date checkoutDay, Integer roomCount) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Integer updateCount = 0;
		try{
			session.beginTransaction();
			Query query = session.createQuery("UPDATE RoomsVO set bookedNum = bookedNum + ? where hotelId = ? and roomTypeId = ? and  roomDate between ? and ?");
			query.setParameter(0, roomCount);
			query.setParameter(1, hotelId);
			query.setParameter(2, roomTypeId);
			query.setParameter(3, checkinDay);
			query.setParameter(4, checkoutDay);
			updateCount = query.executeUpdate();
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		return updateCount;
	}

	@Override
	public Integer updatePrice(List<RoomsVO> roomsList) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Integer updateCount = 0;
		try{
			session.beginTransaction();
			Query query = session.createQuery("UPDATE RoomsVO SET price = ? WHERE roomId = ?");
			for(RoomsVO vo : roomsList){
				query.setParameter(0, vo.getPrice());
				query.setParameter(1, vo.getRoomId());
				Integer count = query.executeUpdate();
				updateCount += count;
			}
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		return updateCount;
	}

	@Override
	public Map<String, Integer> getPerPrice(Integer roomId, Integer stayDayNum) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Map<String,Integer> perPrice = new TreeMap<String,Integer>();
		try{
			session.beginTransaction();
			Query query = session.createQuery("select roomDate,price from RoomsVO where roomId between ? and ? order by roomDate");
			Integer roomIdEnd = roomId + (stayDayNum - 1);
			query.setParameter(0, roomId);
			query.setParameter(1, roomIdEnd);
			List<Object[]> listObj = query.list();
			for(Object[] arrayObj : listObj){
				perPrice.put(((Date)arrayObj[0]).toString(), (Integer)arrayObj[1]);
			}
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		return perPrice;
	}

	@Override
	public Map<String, Integer> getPerPrice(Integer hotelId, Integer roomTypeId, Date checkinDay, Date checkoutDay) {
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		Map<String,Integer> perPrice = new TreeMap<String,Integer>();
		try{
			session.beginTransaction();
			Query query = session.createQuery("select roomDate, price from RoomsVO where hotelId = ? and roomTypeId = ? and  roomDate between ? and ? order by roomDate");
			query.setParameter(0, hotelId);
			query.setParameter(1, roomTypeId);
			query.setParameter(2, checkinDay);
			query.setParameter(3, checkoutDay);
			List<Object[]> listObj = query.list();
			for(Object[] arrayObj : listObj){
				perPrice.put(((Date)arrayObj[0]).toString(), (Integer)arrayObj[1]);
			}
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		return perPrice;
	}

//	RESTful用
	@Override
	public RoomsVO findRoomForREST(Integer hotelId, Integer peopleNum, Date star, Date end, String roomName) {

		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		List<RoomsVO> list = new ArrayList<RoomsVO>();
		RoomsVO vo = null;
		try{
			session.beginTransaction();
			Query query = session.createSQLQuery("{call indexQueryGetRoomREST(?,?,?,?,?)}");
			query.setParameter(0, hotelId);
			query.setParameter(1, star);
			query.setParameter(2, end);
			query.setParameter(3, peopleNum);
			query.setParameter(4, roomName);
			
			List<Object[]> listObj = query.list();
			for(Object[] arrayObj : listObj){
				vo = new RoomsVO();
				vo.setRoomId((Integer)arrayObj[0]);
				vo.setRoomTypeId((Integer)arrayObj[1]);
				vo.setHotelId((Integer)arrayObj[2]);
				vo.setRoomDate((Date)arrayObj[3]);
				vo.setRoomTypeName((String)arrayObj[4]);
				vo.setPeopleNum((Integer)arrayObj[5]);
				vo.setBookedNum((Integer)arrayObj[6]);
				vo.setRoomNumber((Integer)arrayObj[7]);
				vo.setPrice((Integer)arrayObj[8]);
				vo.setBreakfast((Boolean)arrayObj[9]);
				vo.setDinner((Boolean)arrayObj[10]);
				vo.setAfternoonTea((Boolean)arrayObj[11]);
				vo.setBedAddable((Boolean)arrayObj[12]);
				vo.setPricePerPerson((Integer)arrayObj[13]);
				vo.setRemark((String)arrayObj[14]);
//				list.add(vo);
			}
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		return vo;
	}
	
////	REST用
//	public List<RoomsVO> findRoomsForREST(Integer hotelId, Integer peopleNum, Date star, Date end) {
//		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
//		List<RoomsVO> list = new ArrayList<RoomsVO>();
//		try{
//			session.beginTransaction();
//			Query query = session.createSQLQuery("{call indexQueryGetRoom(?,?,?,?)}");
//			query.setParameter(0, hotelId);
//			query.setParameter(1, star);
//			query.setParameter(2, end);
//			query.setParameter(3, peopleNum);
//			List<Object[]> listObj = query.list();
//			for(Object[] arrayObj : listObj){
//				RoomsVO vo = new RoomsVO();
////				vo.setRoomId((Integer)arrayObj[0]);
////				vo.setRoomTypeId((Integer)arrayObj[1]);
////				vo.setHotelId((Integer)arrayObj[2]);
////				vo.setRoomDate((Date)arrayObj[3]);
//				vo.setRoomTypeName((String)arrayObj[4]);
//				vo.setPeopleNum((Integer)arrayObj[5]);
////				vo.setBookedNum((Integer)arrayObj[6]);
////				vo.setRoomNumber((Integer)arrayObj[7]);
//				vo.setPrice((Integer)arrayObj[8]);
//				vo.setBreakfast((Boolean)arrayObj[9]);
//				vo.setDinner((Boolean)arrayObj[10]);
//				vo.setAfternoonTea((Boolean)arrayObj[11]);
//				vo.setBedAddable((Boolean)arrayObj[12]);
//				vo.setPricePerPerson((Integer)arrayObj[13]);
//				vo.setRemark((String)arrayObj[14]);
//				list.add(vo);
//			}
//			session.getTransaction().commit();
//		}catch(RuntimeException e){
//			session.getTransaction().rollback();
//			throw e;
//		}
//		return list;
//	}

}
