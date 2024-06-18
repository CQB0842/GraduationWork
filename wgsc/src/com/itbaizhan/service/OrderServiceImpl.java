package com.itbaizhan.service;

import java.util.*;

import com.itbaizhan.ORM.*;
import com.itbaizhan.base.*;

import org.hibernate.*;

public class OrderServiceImpl extends BaseLog implements OrderService {

	
	public boolean addOrder(Orders order) throws Exception {
		Session session = MySessionFactory.getSession();
		Transaction tx = null;
		boolean status = false;
		try{
			tx = session.beginTransaction();
			session.save(order);
			tx.commit();
			status=true;
		}catch(Exception ex){
			if(tx!=null)tx.rollback();
		
			ex.printStackTrace();
		}finally{
			MySessionFactory.closeSession();
		}	
		return status;
	}

	
	public List browseOrder(Member member) throws Exception {
		Session session = MySessionFactory.getSession();
		Transaction tx = null;
		List list = null;
		try{
			Query query = session.createQuery("from Orders as a where a.member=:member");
			tx = session.beginTransaction();
			query.setEntity("member", member);
			list = query.list();
			if (!Hibernate.isInitialized(list))Hibernate.initialize(list);
			tx.commit();			
		}catch(Exception ex){
			if(tx!=null)tx.rollback();
			
			ex.printStackTrace();
		}finally{
			MySessionFactory.closeSession();
		}	
		return list;
	}

	
	public List browseOrder() throws Exception {
		Session session = MySessionFactory.getSession();
		Transaction tx = null;
		List list = null;
		try{
			Query query = session.createQuery("from Orders as a order by a.id desc");
			tx = session.beginTransaction();
			list = query.list();
			if (!Hibernate.isInitialized(list))Hibernate.initialize(list);
			tx.commit();			
		}catch(Exception ex){
			if(tx!=null)tx.rollback();
		
			ex.printStackTrace();
		}finally{
			MySessionFactory.closeSession();
		}	
		return list;
	}

	
	public List browseOrderMer(Cart cart) throws Exception {
		Session session = MySessionFactory.getSession();
		Transaction tx = null;
		List result = null;
		try{
			
			String hql ="from Cartselectedmer as a where a.cart=:cartid";
			Query query = session.createQuery(hql);
			query.setInteger("cartid", cart.getId().intValue());
			tx = session.beginTransaction();
			result = query.list();
			if(!Hibernate.isInitialized(result)){
				Hibernate.initialize(result);
			}
			tx.commit();
		}catch(Exception ex){
			if(tx!=null)tx.rollback();
			
			ex.printStackTrace();
		}finally{
			MySessionFactory.closeSession();
		}	
		return result;
	}	


	public boolean delOrder(Integer id) throws Exception {
		Session session = MySessionFactory.getSession();
		Transaction tx = null;
		boolean status = false;
		try{
			tx = session.beginTransaction();
			Orders order = (Orders)session.load(Orders.class, id);
			session.delete(order);
			tx.commit();
			status = true;
		}catch(Exception ex){
			if(tx!=null)tx.rollback();
			
			ex.printStackTrace();
		}finally{
			MySessionFactory.closeSession();
		}	
		return status;
	}

	
	public Orders loadOrder(Integer id) throws Exception {
		Session session = MySessionFactory.getSession();
		Transaction tx = null;
		Orders order = null;
		try{
			tx = session.beginTransaction();
			order = (Orders)session.get(Orders.class, id);
			tx.commit();
		}catch(Exception ex){
			if(tx!=null)tx.rollback();
			
			ex.printStackTrace();
		}finally{
			MySessionFactory.closeSession();
		}	
		return order;
	}


	public boolean updateOrder(Orders order) throws Exception {
		Session session = MySessionFactory.getSession();
		Transaction tx = null;
		boolean status = false;
		try{
			tx = session.beginTransaction();
			session.update(order);
			tx.commit();
			status=true;
		}catch(Exception ex){
			if(tx!=null)tx.rollback();
		
			ex.printStackTrace();
		}finally{
			MySessionFactory.closeSession();
		}	
		return status;
	}
}
