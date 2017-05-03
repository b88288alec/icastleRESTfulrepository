package com.icastle.members.model;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import hibernate.util.HibernateUtil;

public class MembersHibernateDAO implements MembersDAO_interface {

	private String orderdata = "from MembersVO where email=?";

	@Override
	public List<MembersVO> findByPrimaryKey(String email) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		List<MembersVO> result = null;
		try{
			session.beginTransaction();
			Query query = session.createQuery(orderdata);
			query.setParameter(0, email);
			result = query.list();
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			throw e;
		}
		return result;
	}

}
