package com.icastle.members.model;

import java.sql.Date;
import java.util.List;



public class MembersService {

	MembersDAO_interface dao = null;
	
	public MembersService (){
		dao = new MembersHibernateDAO();
	}

// 查詢一筆
   public List<MembersVO> findByPrimaryKey(String email){
	   return dao.findByPrimaryKey(email);
   }
}
