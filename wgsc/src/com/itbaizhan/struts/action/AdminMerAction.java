/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.itbaizhan.struts.action;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.*;

import org.apache.struts.action.*;
import org.apache.struts.upload.FormFile;

import com.itbaizhan.ORM.*;
import com.itbaizhan.base.*;
import com.itbaizhan.service.*;
import com.itbaizhan.struts.form.MerForm;

/** 
 * MyEclipse Struts
 * XDoclet definition:
 * @struts.action path="/Admin/adminMer" name="merForm" input="/Admin/adminAddMer.jsp" parameter="method" scope="request" validate="true"
 */
public class AdminMerAction extends BaseAction {
	/*
	 * Generated Methods
	 */

	/** 
	 * Method execute
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return ActionForward
	 */
	public ActionForward addMer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		MerForm merForm = (MerForm) form;
		Merchandise mer = new Merchandise();
		MerService service = new MerServiceImpl();
		ActionMessages msgs = new ActionMessages();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		mer.setMerName(merForm.getMerName().trim());
		mer.setMerModel(merForm.getMerModel().trim());		
		mer.setPrice(merForm.getPrice());
		mer.setSpecial(merForm.getSpecial());
		mer.setSprice(merForm.getSprice());
		mer.setMerDesc(merForm.getMerDesc().trim());
		mer.setManufacturer(merForm.getManufacturer().trim());
			
		
        String dir = request.getRealPath("/Picture");
        
        String filename = null;
     
        FormFile file = merForm.getPicture();
        
        InputStream in = null;
    
        OutputStream out = null;
		try{
			mer.setLeaveFactoryDate(df.parse(merForm.getLeaveFactoryDate()));
			mer.setCategory(service.loadCategory(new Integer(merForm.getCategory())));
			filename = file.getFileName();
			if (file==null||filename.length()<1){
			
			}else{				
				in = file.getInputStream();
				out = new FileOutputStream(dir+"/"+filename);
				int readed = 0;
				byte[] buffer= new byte[1024];
				while ((readed=in.read(buffer,0,1024))!=-1){
				         out.write(buffer,0,readed);
				}
				
				mer.setPicture("/Picture/"+filename);
			}  			
			boolean status = service.addMer(mer);
			if (status){
				msgs.add("addMerStatus",new ActionMessage(Constants.ADDMER_SUC_KEY));
			}else{				
				msgs.add("addMerStatus",new ActionMessage(Constants.ADDMER_FAIL_KEY));
			}
			saveErrors(request, msgs);
		}catch(Exception ex){	
		
			ex.printStackTrace();
		}finally{
			try{
				if (in!=null)in.close();
				if (out!=null)out.close();
			}catch(Exception ex){	
				
				ex.printStackTrace();
			}
		}		
		return mapping.findForward("addMer");
	}
	
	public ActionForward browseMer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		List list = null;
		MerService service = new MerServiceImpl();
		Admin admin = (Admin)request.getSession().getAttribute("Admin");
		if (admin==null||(admin.getAdminType().intValue()!=1 && admin.getAdminType().intValue() !=4)){
			return mapping.findForward("sorry");
		}
		String hql ="from Merchandise as a order by a.id";
		try{			
			list = service.browseMer(hql);
			request.setAttribute("merList", list);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return mapping.findForward("browse");			
	}
	
	public ActionForward delMer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActionMessages msgs = new ActionMessages();
		MerService service = new MerServiceImpl();
		String p = request.getParameter("id");
		Integer id = null;
		if(p!=null){
			id = new Integer(p);
		}else{
			id = new Integer(0);
		}
		try{
			boolean status = service.delMer(id);
			if (status){
				msgs.add("delMerStatus",new ActionMessage(Constants.DELMER_SUC_KEY));
			}else{				
				msgs.add("delMerStatus",new ActionMessage(Constants.DELMER_FAIL_KEY));
			}			
			saveErrors(request, msgs);
		}catch(Exception ex){
		
			ex.printStackTrace();
		}
		return mapping.findForward("delete");		
	}
	
	public ActionForward loadMer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		MerService service = new MerServiceImpl();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Merchandise mer = null;
		String p = request.getParameter("id");
		Integer id = null;
		if(p!=null){
			id = new Integer(p);
		}else{
			id = new Integer(0);
		}
		try{
			mer = service.loadMer(id);
			if(mer!=null){
				form = new MerForm();
				((MerForm)form).setMerName(mer.getMerName().trim());
				((MerForm)form).setMerModel(mer.getMerModel().trim());
				((MerForm)form).setMerDesc(mer.getMerDesc().trim());
				((MerForm)form).setPrice(mer.getPrice());
				((MerForm)form).setSpecial(mer.getSpecial());
				((MerForm)form).setManufacturer(mer.getManufacturer().trim());
				((MerForm)form).setLeaveFactoryDate(df.format(mer.getLeaveFactoryDate()));
				((MerForm)form).setSprice(mer.getSprice());
				request.setAttribute("merForm", form);
				request.setAttribute("id", mer.getId());
				request.setAttribute("cateId", mer.getCategory().getId());
			}
		}catch(Exception ex){
			
			ex.printStackTrace();
		}
		return mapping.findForward("modify");		
	}
	
	public ActionForward modiMer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActionMessages msgs = new ActionMessages();
		MerForm merForm = (MerForm) form;
		Merchandise mer = null;
		MerService service = new MerServiceImpl();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String p = request.getParameter("id");
		Integer id = null;
		if(p!=null){
			id = new Integer(p);
		}else{
			id = new Integer(0);
		}			
		
        String dir = request.getRealPath("/Picture");
       
        String filename = null;
       
        FormFile file = merForm.getPicture();
       
        InputStream in = null;
        
        OutputStream out = null;
		try{
			mer = service.loadMer(id);
			if (mer!=null){
				mer.setId(id);
				mer.setMerName(merForm.getMerName().trim());
				mer.setMerModel(merForm.getMerModel().trim());		
				mer.setPrice(merForm.getPrice());
				mer.setSpecial(merForm.getSpecial());
				mer.setSprice(merForm.getSprice());
				mer.setMerDesc(merForm.getMerDesc().trim());
				mer.setManufacturer(merForm.getManufacturer().trim());
				mer.setLeaveFactoryDate(df.parse(merForm.getLeaveFactoryDate()));
				mer.setCategory(service.loadCategory(new Integer(merForm.getCategory())));
				filename = file.getFileName();
				if (file==null||filename.length()<1){
					
				}else{				
					in = file.getInputStream();
					out = new FileOutputStream(dir+"/"+filename);
					int readed = 0;
					byte[] buffer= new byte[1024];
					while ((readed=in.read(buffer,0,1024))!=-1){
					         out.write(buffer,0,readed);
					}
				
					mer.setPicture("/Picture/"+filename);
				}  			
				boolean status = service.updateMer(mer);
				if (status){
					msgs.add("modiMerStatus",new ActionMessage(Constants.MODIMER_SUC_KEY));
				}else{				
					msgs.add("modiMerStatus",new ActionMessage(Constants.MODIMER_FAIL_KEY));
				}				
			}else{
				msgs.add("modiMerStatus",new ActionMessage(Constants.MODIMER_FAIL_KEY));
			}
			saveErrors(request, msgs);
		}catch(Exception ex){	
			ex.printStackTrace();
		}finally{
			try{
				if (in!=null)in.close();
				if (out!=null)out.close();
			}catch(Exception ex){	
				ex.printStackTrace();
			}
		}		
		return mapping.findForward("modify");		
	}
	
	public ActionForward showMer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		MerService service = new MerServiceImpl();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Merchandise mer = null;
		String p = request.getParameter("id");
		Integer id = null;
		if(p!=null){
			id = new Integer(p);
		}else{
			id = new Integer(0);
		}
		try{
			mer = service.loadMer(id);
			if(mer!=null){
				form = new MerForm();
				((MerForm)form).setMerName(mer.getMerName().trim());
				((MerForm)form).setMerModel(mer.getMerModel().trim());
				((MerForm)form).setMerDesc(mer.getMerDesc().trim());
				((MerForm)form).setPrice(mer.getPrice());
				((MerForm)form).setSpecial(mer.getSpecial());
				((MerForm)form).setManufacturer(mer.getManufacturer().trim());
				((MerForm)form).setLeaveFactoryDate(df.format(mer.getLeaveFactoryDate()));
				((MerForm)form).setSprice(mer.getSprice());
				request.setAttribute("merForm", form);
				request.setAttribute("cateId", mer.getCategory().getId());
				if (mer.getPicture()!=null){
					request.setAttribute("picture", ".."+mer.getPicture().trim());
				}else{
					request.setAttribute("picture", "../images/default.jpg");
				}				
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return mapping.findForward("show");		
	}
	
	public ActionForward browseSMer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		List list = null;
		MerService service = new MerServiceImpl();
		Admin admin = (Admin)request.getSession().getAttribute("Admin");
		if (admin==null || (admin.getAdminType().intValue()!=1 && admin.getAdminType().intValue()!=4)){
			return mapping.findForward("sorry");
		}
		String hql ="from Merchandise as a where a.special=1 order by a.id";
		try{			
			list = service.browseMer(hql);
			request.setAttribute("merList", list);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return mapping.findForward("browseSMer");		
	}
	
	public ActionForward addSMer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		MerForm merForm = (MerForm) form;
		Merchandise mer = new Merchandise();
		MerService service = new MerServiceImpl();
		ActionMessages msgs = new ActionMessages();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		mer.setMerName(merForm.getMerName().trim());
		mer.setMerModel(merForm.getMerModel().trim());		
		mer.setPrice(merForm.getPrice());
		mer.setSpecial(merForm.getSpecial());
		mer.setSprice(merForm.getSprice());
		mer.setMerDesc(merForm.getMerDesc().trim());
		mer.setManufacturer(merForm.getManufacturer().trim());
			
		
        String dir = request.getRealPath("/Picture");
    
        String filename = null;
        
        FormFile file = merForm.getPicture();
       
        InputStream in = null;
        
        OutputStream out = null;
		try{
			mer.setLeaveFactoryDate(df.parse(merForm.getLeaveFactoryDate()));
			mer.setCategory(service.loadCategory(new Integer(merForm.getCategory())));
			filename = file.getFileName();
			if (file==null||filename.length()<1){
				
			}else{				
				in = file.getInputStream();
				out = new FileOutputStream(dir+"/"+filename);
				int readed = 0;
				byte[] buffer= new byte[1024];
				while ((readed=in.read(buffer,0,1024))!=-1){
				         out.write(buffer,0,readed);
				}
			
				mer.setPicture("/Picture/"+filename);
			}  			
			boolean status = service.addMer(mer);
			if (status){
				msgs.add("addSMerStatus",new ActionMessage(Constants.ADDSMER_SUC_KEY));
			}else{				
				msgs.add("addSMerStatus",new ActionMessage(Constants.ADDSMER_FAIL_KEY));
			}
			saveErrors(request, msgs);
		}catch(Exception ex){	
			
			ex.printStackTrace();
		}finally{
			try{
				if (in!=null)in.close();
				if (out!=null)out.close();
			}catch(Exception ex){	
				
				ex.printStackTrace();
			}
		}		
		return mapping.findForward("addSMer");		
	}
	
	public ActionForward loadSMer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		MerService service = new MerServiceImpl();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Merchandise mer = null;
		String p = request.getParameter("id");
		Integer id = null;
		if(p!=null){
			id = new Integer(p);
		}else{
			id = new Integer(0);
		}
		try{
			mer = service.loadMer(id);
			if(mer!=null){
				form = new MerForm();
				((MerForm)form).setMerName(mer.getMerName().trim());
				((MerForm)form).setMerModel(mer.getMerModel().trim());
				((MerForm)form).setMerDesc(mer.getMerDesc().trim());
				((MerForm)form).setPrice(mer.getPrice());
				((MerForm)form).setSpecial(mer.getSpecial());
				((MerForm)form).setManufacturer(mer.getManufacturer().trim());
				((MerForm)form).setLeaveFactoryDate(df.format(mer.getLeaveFactoryDate()));
				((MerForm)form).setSprice(mer.getSprice());
				request.setAttribute("merForm", form);
				request.setAttribute("id", mer.getId());
				request.setAttribute("cateId", mer.getCategory().getId());
			}
		}catch(Exception ex){
			
			ex.printStackTrace();
		}
		return mapping.findForward("modifySMer");		
	}
	
	public ActionForward modiSMer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActionMessages msgs = new ActionMessages();
		MerForm merForm = (MerForm) form;
		Merchandise mer = null;
		MerService service = new MerServiceImpl();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String p = request.getParameter("id");
		Integer id = null;
		if(p!=null){
			id = new Integer(p);
		}else{
			id = new Integer(0);
		}			
		
        String dir = request.getRealPath("/Picture");
       
        String filename = null;
        
        FormFile file = merForm.getPicture();
       
        InputStream in = null;
        
        OutputStream out = null;
		try{
			mer = service.loadMer(id);
			if (mer!=null){
				mer.setId(id);
				mer.setMerName(merForm.getMerName().trim());
				mer.setMerModel(merForm.getMerModel().trim());		
				mer.setPrice(merForm.getPrice());
				mer.setSpecial(merForm.getSpecial());
				mer.setSprice(merForm.getSprice());
				mer.setMerDesc(merForm.getMerDesc().trim());
				mer.setManufacturer(merForm.getManufacturer().trim());
				mer.setLeaveFactoryDate(df.parse(merForm.getLeaveFactoryDate()));
				mer.setCategory(service.loadCategory(new Integer(merForm.getCategory())));
				filename = file.getFileName();
				if (file==null||filename.length()<1){
				}else{				
					in = file.getInputStream();
					out = new FileOutputStream(dir+"/"+filename);
					int readed = 0;
					byte[] buffer= new byte[1024];
					while ((readed=in.read(buffer,0,1024))!=-1){
					         out.write(buffer,0,readed);
					}
					mer.setPicture("/Picture/"+filename);
				}  			
				boolean status = service.updateMer(mer);
				if (status){
					msgs.add("modiSMerStatus",new ActionMessage(Constants.MODISMER_SUC_KEY));
				}else{				
					msgs.add("modiSMerStatus",new ActionMessage(Constants.MODISMER_FAIL_KEY));
				}				
			}else{
				msgs.add("modiSMerStatus",new ActionMessage(Constants.MODISMER_FAIL_KEY));
			}
			saveErrors(request, msgs);
		}catch(Exception ex){	
			ex.printStackTrace();
		}finally{
			try{
				if (in!=null)in.close();
				if (out!=null)out.close();
			}catch(Exception ex){	
				ex.printStackTrace();
			}
		}		
		return mapping.findForward("modifySMer");		
	}
	
	public ActionForward showSMer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		MerService service = new MerServiceImpl();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Merchandise mer = null;
		String p = request.getParameter("id");
		Integer id = null;
		if(p!=null){
			id = new Integer(p);
		}else{
			id = new Integer(0);
		}
		try{
			mer = service.loadMer(id);
			if(mer!=null){
				form = new MerForm();
				((MerForm)form).setMerName(mer.getMerName().trim());
				((MerForm)form).setMerModel(mer.getMerModel().trim());
				((MerForm)form).setMerDesc(mer.getMerDesc().trim());
				((MerForm)form).setPrice(mer.getPrice());
				((MerForm)form).setSpecial(mer.getSpecial());
				((MerForm)form).setManufacturer(mer.getManufacturer().trim());
				((MerForm)form).setLeaveFactoryDate(df.format(mer.getLeaveFactoryDate()));
				((MerForm)form).setSprice(mer.getSprice());
				request.setAttribute("merForm", form);
				request.setAttribute("cateId", mer.getCategory().getId());
				if (mer.getPicture()!=null){
					request.setAttribute("picture", ".."+mer.getPicture().trim());
				}else{
					request.setAttribute("picture", "../images/default.jpg");
				}				
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return mapping.findForward("showSMer");			
	}
	
	public ActionForward delSMer(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActionMessages msgs = new ActionMessages();
		MerService service = new MerServiceImpl();
		String p = request.getParameter("id");
		Integer id = null;
		if(p!=null){
			id = new Integer(p);
		}else{
			id = new Integer(0);
		}
		try{
			boolean status = service.delMer(id);
			if (status){
				msgs.add("delSMerStatus",new ActionMessage(Constants.DELSMER_SUC_KEY));
			}else{				
				msgs.add("delSMerStatus",new ActionMessage(Constants.DELSMER_FAIL_KEY));
			}			
			saveErrors(request, msgs);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return mapping.findForward("deleteSMer");		
	}
}