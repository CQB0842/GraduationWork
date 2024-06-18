package com.itbaizhan.service;

import java.util.*;

import com.itbaizhan.ORM.*;


public interface MerService {	

	public List browseCategory() throws Exception;	
		
	public Category loadCategory(Integer id) throws Exception;	
	
	public boolean delCategory(Integer id) throws Exception;	
		
	public boolean addCategory(Category cate) throws Exception;	
	
	public boolean updateCategory(Category cate) throws Exception;
	
	
	public List browseMer(String hql) throws Exception;	

	public Merchandise loadMer(Integer id) throws Exception;	

	public boolean delMer(Integer id) throws Exception;	

	public boolean addMer(Merchandise mer) throws Exception;	

	public boolean updateMer(Merchandise mer) throws Exception;
	

	public List browseMer(int pageSize,int pageNo,int cateId,boolean isSpecial) throws Exception;

	public List browseMer(int pageSize,int pageNo,String hql) throws Exception;	
	
	public int countRecord(String hql) throws Exception;	
}