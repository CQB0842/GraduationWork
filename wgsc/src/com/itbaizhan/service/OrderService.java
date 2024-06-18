package com.itbaizhan.service;

import com.itbaizhan.ORM.*;

import java.util.*;

public interface OrderService {
	
	public boolean addOrder(Orders order) throws Exception;
	
	public List browseOrder(Member member) throws Exception;

	public List browseOrder() throws Exception;

	public List browseOrderMer(Cart cart) throws Exception;			

	public boolean delOrder(Integer id) throws Exception;	

	public Orders loadOrder(Integer id) throws Exception;

	public boolean updateOrder(Orders order) throws Exception;
}
