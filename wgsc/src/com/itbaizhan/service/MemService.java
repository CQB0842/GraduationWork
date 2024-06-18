package com.itbaizhan.service;

import java.util.*;

import com.itbaizhan.ORM.*;

public interface MemService {
	
	public Member memLogin(String loginName,String loginPwd) throws Exception;	

	public List browseMemberLevel() throws Exception;
	
	public Memberlevel loadMemberLevel(Integer id) throws Exception;

	public boolean chkLoginName(String loginName) throws Exception;	
	
	public boolean addMember(Member member) throws Exception;
		
	public boolean updateMember(Member member) throws Exception;
	
	
	public List browseMember() throws Exception;
		
	public boolean delMember(Integer id) throws Exception;

	public Member loadMember(Integer id) throws Exception;	
}
