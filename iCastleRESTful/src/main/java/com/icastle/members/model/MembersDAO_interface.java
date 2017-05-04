package com.icastle.members.model;

import java.util.List;

public interface MembersDAO_interface {
      public List<MembersVO> findByPrimaryKey(String email);  //查詢會員一筆
}