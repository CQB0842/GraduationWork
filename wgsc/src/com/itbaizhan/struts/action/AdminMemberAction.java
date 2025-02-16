/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.itbaizhan.struts.action;

import java.util.*;

import javax.servlet.http.*;

import org.apache.struts.action.*;

import com.itbaizhan.ORM.*;
import com.itbaizhan.base.*;
import com.itbaizhan.service.*;

/** 
 * MyEclipse Struts
 * XDoclet definition:
 * @struts.action parameter="method" validate="true"
 * @struts.action-forward name="browseWord" path="/Admin/adminWord.jsp"
 * @struts.action-forward name="browseMember" path="/Admin/adminMember.jsp"
 * @struts.action-forward name="viewWord" path="/Admin/WordInfo.jsp"
 * @struts.action-forward name="viewMember" path="/Admin/MemberInfo.jsp"
 */
public class AdminMemberAction extends BaseAction {
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
	public ActionForward browseMember(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Admin admin = (Admin)request.getSession().getAttribute("Admin");
		if (admin==null||(admin.getAdminType().intValue() !=3  && admin.getAdminType().intValue()!=4)){
			return mapping.findForward("sorry");
		}
		MemService service = new MemServiceImpl();
		List result = null;
		try{
			result = service.browseMember();
			if(result!=null&&result.size()>0)request.setAttribute("memberList", result);	
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return mapping.findForward("browseMember");
	}
	
	public ActionForward viewMember(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		MemService service = new MemServiceImpl();
		Member member = null;
		String p = request.getParameter("id");
		Integer id = null;
		if(p!=null){
			id = new Integer(p);
		}else{
			id = new Integer(0);
		}		
		try{
			member = service.loadMember(id);
			if(member!=null)request.setAttribute("member", member);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return mapping.findForward("viewMember");		
	}
	
	public ActionForward delMember(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		MemService service = new MemServiceImpl();
		ActionMessages msgs = new ActionMessages();
		String p = request.getParameter("id");
		Integer id = null;
		if(p!=null){
			id = new Integer(p);
		}else{
			id = new Integer(0);
		}		
		try{
			boolean status = false;
			status = service.delMember(id);
			if (status){
				msgs.add("delMemberStatus",new ActionMessage(Constants.MEMBER_DEL_SUC_KEY));
			}else{				
				msgs.add("delMemberStatus",new ActionMessage(Constants.MEMBER_DEL_FAIL_KEY));
			}
			saveErrors(request, msgs);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return new ActionForward("/Admin/adminMember.do?method=browseMember");		
	}
	
	public ActionForward browseWord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		Admin admin = (Admin)request.getSession().getAttribute("Admin");
		if (admin==null||(admin.getAdminType().intValue()!=3  && admin.getAdminType().intValue()!=4)){
			return mapping.findForward("sorry");
		}
		WordService service = new WordServiceImpl();
		List result = null;
		try{
			result = service.browseWord();
			if(result!=null&&result.size()>0)request.setAttribute("wordList", result);	
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return mapping.findForward("browseWord");		
	}
	
	public ActionForward viewWord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		WordService service = new WordServiceImpl();
		Leaveword word = null;
		String p = request.getParameter("id");
		Integer id = null;
		if(p!=null){
			id = new Integer(p);
		}else{
			id = new Integer(0);
		}		
		try{
			word = service.loadWord(id);
			if(word!=null)request.setAttribute("word", word);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return mapping.findForward("viewWord");		
	}
	
	public ActionForward answerWord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		WordService service = new WordServiceImpl();
		ActionMessages msgs = new ActionMessages();
		String answerContent = request.getParameter("answerContent");
		Admin admin = (Admin)request.getSession().getAttribute("Admin");		
		Leaveword word = null;
		String p = request.getParameter("id");
		Integer id = null;
		if(p!=null){
			id = new Integer(p);
		}else{
			id = new Integer(0);
		}
		try{
			word = service.loadWord(id);
			word.setAdmin(admin);
			word.setAnswerContent(answerContent);
			word.setAnswerDate(new Date());
			boolean status = service.updateWord(word);
			if (status){
				msgs.add("answerWordStatus",new ActionMessage(Constants.WORD_ANSWER_SUC_KEY));
			}else{				
				msgs.add("answerWordStatus",new ActionMessage(Constants.WORD_ANSWER_FAIL_KEY));
			}
			saveErrors(request, msgs);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return new ActionForward("/Admin/adminMember.do?method=browseWord");		
	}
	
	public ActionForward delWord(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		WordService service = new WordServiceImpl();
		ActionMessages msgs = new ActionMessages();
		String p = request.getParameter("id");
		Integer id = null;
		if(p!=null){
			id = new Integer(p);
		}else{
			id = new Integer(0);
		}		
		try{
			boolean status = false;
			status = service.delWord(id);
			if (status){
				msgs.add("delWordStatus",new ActionMessage(Constants.WORD_DEL_SUC_KEY));
			}else{				
				msgs.add("delWordStatus",new ActionMessage(Constants.WORD_DEL_FAIL_KEY));
			}
			saveErrors(request, msgs);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return new ActionForward("/Admin/adminMember.do?method=browseWord");		
	}
}