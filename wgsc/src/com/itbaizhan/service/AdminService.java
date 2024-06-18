package com.itbaizhan.service;

import com.itbaizhan.ORM.*;

import java.util.*;


public interface AdminService {

	
	public Admin adminLogin(String loginName,String loginPwd) throws Exception;
	
	
	public List browseAdmin() throws Exception;	
		
	public Admin loadAdmin(Integer id) throws Exception;	
	
	public boolean delAdmin(Integer id) throws Exception;	
	
	public boolean addAdmin(Admin admin) throws Exception;	
	
	public boolean updateAdmin(Admin admin) throws Exception;	
}