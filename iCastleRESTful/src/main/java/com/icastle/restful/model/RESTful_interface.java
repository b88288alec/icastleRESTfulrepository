package com.icastle.restful.model;

public interface RESTful_interface {

	public boolean search(Integer id);
	public void insert(RESTfulVO restVO);
	public void update(RESTfulVO restVO);
	public void delete(Integer id);
	public RESTfulVO select(Integer id);
	
}
