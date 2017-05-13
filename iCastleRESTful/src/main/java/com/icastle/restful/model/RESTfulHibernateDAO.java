package com.icastle.restful.model;

import org.hibernate.Session;

import hibernate.util.HibernateUtil;

public class RESTfulHibernateDAO implements RESTful_interface{

	@Override
	public boolean search(Integer id) {
		
		RESTfulVO result = null;
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			result = (RESTfulVO)session.get(RESTfulVO.class, id);
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		
		if(result == null){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public RESTfulVO select(Integer id) {
		
		RESTfulVO result = null;
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			result = (RESTfulVO)session.get(RESTfulVO.class, id);
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public void insert(RESTfulVO restVO) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.save(restVO);
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		
	}
	
	@Override
	public void update(RESTfulVO restVO) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			session.saveOrUpdate(restVO);
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		
	}

	@Override
	public void delete(Integer id) {
		
		Session session = HibernateUtil.getSessionFactory().getCurrentSession();
		try{
			session.beginTransaction();
			RESTfulVO result = (RESTfulVO)session.get(RESTfulVO.class, id);
			session.delete(result);
			session.getTransaction().commit();
		}catch(RuntimeException e){
			session.getTransaction().rollback();
			e.printStackTrace();
		}
		
	}

}
