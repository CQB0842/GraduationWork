package com.itbaizhan.service;

import com.itbaizhan.ORM.*;

import java.util.*;

public interface WordService {
	
	public boolean addWord(Leaveword word) throws Exception;
	
	public List browseWord(int pageSize,int pageNo) throws Exception;
	
	public List browseWord() throws Exception;

	public int countWord() throws Exception;	

	public boolean delWord(Integer id) throws Exception;	
	
	public Leaveword loadWord(Integer id) throws Exception;
	
	public boolean updateWord(Leaveword word) throws Exception;
}
