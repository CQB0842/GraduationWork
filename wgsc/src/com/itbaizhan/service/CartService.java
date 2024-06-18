package com.itbaizhan.service;

import com.itbaizhan.ORM.*;

import java.util.*;

public interface CartService {
	
	public boolean addCart(Member member,Merchandise mer,int number) throws Exception;

	public List browseCart(Member member) throws Exception;		
	
	public boolean clearCart(Member member) throws Exception;		
	
	public boolean modiCart(Integer id,int number) throws Exception;	

	public boolean delCart(Integer id) throws Exception;
	
	public Cart loadCart(Member member) throws Exception;

	public boolean updateCart(Cart cart) throws Exception;
}
