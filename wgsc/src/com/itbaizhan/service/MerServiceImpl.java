package com.itbaizhan.service;

import java.util.List;

import org.hibernate.*;

import com.itbaizhan.ORM.*;
import com.itbaizhan.base.BaseLog;

public class MerServiceImpl extends BaseLog implements MerService {


	public boolean addCategory(Category cate) throws Exception {
		Session session = MySessionFactory.getSession();
		Transaction tx = null;
		boolean status = false;
		try{
			tx = session.beginTransaction();
			session.save(cate);
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
	
	
	public List browseCategory() throws Exception {
		Session session = MySessionFactory.getSession();
		Transaction tx = null;
		List list = null;
		try{
			Query query = session.createQuery("from Category as a order by a.id");
			tx = session.beginTransaction();
			list = query.list();
			tx.commit();
			if (!Hibernate.isInitialized(list))Hibernate.initialize(list);
		}catch(Exception ex){
			if(tx!=null)tx.rollback();
	
			ex.printStackTrace();
		}finally{
			MySessionFactory.closeSession();
		}	
		return list;
	}
	
	
	public boolean delCategory(Integer id) throws Exception {
		Session session = MySessionFactory.getSession();
		Transaction tx = null;
		boolean status = false;
		try{
			tx = session.beginTransaction();
			Category cate = (Category)session.load(Category.class, id);
			session.delete(cate);
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
	
	
	public Category loadCategory(Integer id) throws Exception {
		Session session = MySessionFactory.getSession();
		Transaction tx = null;
		Category cate = null;
		try{
			tx = session.beginTransaction();
			cate = (Category)session.get(Category.class, id);
			tx.commit();
		}catch(Exception ex){
			if(tx!=null)tx.rollback();

			ex.printStackTrace();
		}finally{
			MySessionFactory.closeSession();
		}	
		return cate;
	}

	public boolean updateCategory(Category cate) throws Exception {
		Session session = MySessionFactory.getSession();
		Transaction tx = null;
		boolean status = false;
		try{
			tx = session.beginTransaction();
			session.update(cate);
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

	public boolean addMer(Merchandise mer) throws Exception {
		Session session = MySessionFactory.getSession();
		Transaction tx = null;
		boolean status = false;
		try{
			tx = session.beginTransaction();
			session.save(mer);
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

	
	public List browseMer(String hql) throws Exception {
		Session session = MySessionFactory.getSession();
		Transaction tx = null;
		List list = null;
		try{
			Query query = session.createQuery(hql);
			tx = session.beginTransaction();
			list = query.list();
			tx.commit();
			if (!Hibernate.isInitialized(list))Hibernate.initialize(list);
		}catch(Exception ex){
			if(tx!=null)tx.rollback();
		
			ex.printStackTrace();
		}finally{
			MySessionFactory.closeSession();
		}	
		return list;
	}

	
	public boolean delMer(Integer id) throws Exception {
		Session session = MySessionFactory.getSession();
		Transaction tx = null;
		boolean status = false;
		try{
			tx = session.beginTransaction();
			Merchandise mer = (Merchandise)session.load(Merchandise.class, id);
			session.delete(mer);
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

	
	public Merchandise loadMer(Integer id) throws Exception {
		Session session = MySessionFactory.getSession();
		Transaction tx = null;
		Merchandise mer = null;
		try{
			tx = session.beginTransaction();
			mer = (Merchandise)session.get(Merchandise.class, id);
			tx.commit();
		}catch(Exception ex){
			if(tx!=null)tx.rollback();
			
			ex.printStackTrace();
		}finally{
			MySessionFactory.closeSession();
		}	
		return mer;
	}

	
	public boolean updateMer(Merchandise mer) throws Exception {
		Session session = MySessionFactory.getSession();
		Transaction tx = null;
		boolean status = false;
		try{
			tx = session.beginTransaction();
			session.update(mer);
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

	
	public List browseMer(int pageSize, int pageNo, int cateId,boolean isSpecial) throws Exception {
		Session session = MySessionFactory.getSession();
		Transaction tx = null;
		List list = null;
		try{
			String hql = "from Merchandise as a";
			if (isSpecial){	//�ؼ���Ʒ
				hql = hql + " where a.special=1";
			}else{ //��ͨ��Ʒ
				hql = hql + " where a.special=0";				
			}
			if (cateId!=0){ //ָ�����
				hql = hql + " and a.category.id="+cateId;
			}
			hql = hql + " order by a.id desc";
			Query query = session.createQuery(hql);
			query.setMaxResults(pageSize);
			query.setFirstResult((pageNo-1)*pageSize);
			tx = session.beginTransaction();
			list = query.list();
			tx.commit();
			if (!Hibernate.isInitialized(list))Hibernate.initialize(list);
		}catch(Exception ex){
			if(tx!=null)tx.rollback();
			
			ex.printStackTrace();
		}finally{
			MySessionFactory.closeSession();
		}	
		return list;
	}


	public List browseMer(int pageSize, int pageNo, String hql) throws Exception {
		Session session = MySessionFactory.getSession();
		Transaction tx = null;
		List list = null;
		try{
			Query query = session.createQuery(hql);
			query.setMaxResults(pageSize);
			query.setFirstResult((pageNo-1)*pageSize);
			tx = session.beginTransaction();
			list = query.list();
			tx.commit();
			if (!Hibernate.isInitialized(list))Hibernate.initialize(list);
		}catch(Exception ex){
			if(tx!=null)tx.rollback();
		
			ex.printStackTrace();
		}finally{
			MySessionFactory.closeSession();
		}	
		return list;
	}
	
	
	public int countRecord(String hql) throws Exception {
		Session session = MySessionFactory.getSession();
		Transaction tx = null;
		int count = 0;
		try{
			tx = session.beginTransaction();
			Query query = session.createQuery(hql);
			query.setMaxResults(1);
			count = ((Integer)query.uniqueResult()).intValue();
			tx.commit();
		}catch(Exception ex){
			if(tx!=null)tx.rollback();
			
			ex.printStackTrace();
		}finally{
			MySessionFactory.closeSession();
		}	
		return count;
	}
}