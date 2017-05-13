package com.icastle.restful.model;

public class RESTfulService {

	RESTful_interface dao;
	
	public RESTfulService(){
		dao = new RESTfulHibernateDAO();
	}
	
//	新增記錄
	public void insert(RESTfulVO restVO){
		dao.insert(restVO);
	}
	
//	判斷是否有重複記錄
	public boolean search(Integer id){
		return dao.search(id);
	}
	
//	從資料庫取出參數
	public RESTfulVO select(Integer id){
		return dao.select(id);
	}
	
//	修改紀錄
	public void update(RESTfulVO restVO){
		dao.update(restVO);
	}
	
//	刪除記錄
	public void delete(Integer id){
		dao.delete(id);
	}
	
}
