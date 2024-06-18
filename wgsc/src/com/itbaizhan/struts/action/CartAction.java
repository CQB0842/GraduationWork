/*
 * Generated by MyEclipse Struts
 * Template path: templates/java/JavaClass.vtl
 */
package com.itbaizhan.struts.action;

import javax.servlet.http.*;

import org.apache.struts.action.*;

import com.itbaizhan.ORM.*;
import com.itbaizhan.base.*;
import com.itbaizhan.service.*;

import java.text.*;
import java.util.*;

/** 
 * MyEclipse Struts
 * XDoclet definition:
 * @struts.action parameter="method" validate="true"
 */
public class CartAction extends BaseAction {
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
	public ActionForward browseCart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		CartService cartService = new CartServiceImpl();
		MerService merService = new MerServiceImpl();
		Map row = null;
		List result = null;
		double money = 0;
		try{
			DecimalFormat df = new DecimalFormat(".##");
			//�жϻ�Ա�Ƿ��ѳɹ���¼
			Member mem = (Member)request.getSession().getAttribute("member");			
			if(mem==null){
				forward = mapping.findForward("memSorry");
			}else{
				List tmp = cartService.browseCart(mem);
				if (tmp!=null && tmp.size()>0){
					result = new ArrayList();
					Iterator it = tmp.iterator();
					Cartselectedmer sel = null;
					Merchandise mer = null;
					while(it.hasNext()){
						row = new HashMap();
						sel = (Cartselectedmer)it.next();
						mer = merService.loadMer(sel.getMerchandise());
						row.put("merId", mer.getId());
						row.put("selId", sel.getId());					
						row.put("merName", mer.getMerName().trim());
						row.put("memprice", Double.valueOf(df.format(sel.getPrice())));
						row.put("price",  Double.valueOf(df.format(mer.getPrice())));						
						row.put("number", sel.getNumber());
						row.put("money", sel.getMoney());
						money = money + sel.getMoney().doubleValue();
						result.add(row);
					}
					request.setAttribute("result", result);	
					request.setAttribute("totalMoney", Double.valueOf(df.format(money)));	
				}
				forward = mapping.findForward("browseCart");
			}			
		}catch(Exception ex){
			
			ex.printStackTrace();
		}
		return forward;
	}

	public ActionForward addCart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		CartService cartService = new CartServiceImpl();
		MerService merService = new MerServiceImpl();
		ActionMessages msgs = new ActionMessages();
		Integer id = null;
		if (request.getParameter("id")!=null){
			id = new Integer(request.getParameter("id"));
		}
		try{
			
			Member mem = (Member)request.getSession().getAttribute("member");
			boolean status = false;
			Merchandise mer = null;
			if(mem==null){
				forward = mapping.findForward("memSorry");
			}else{
				if (id!=null){
					mer = merService.loadMer(id);
					if (mer!=null){
						status = cartService.addCart(mem, mer, 1);
					}
				}
				if (status){
					msgs.add("addCartStatus",new ActionMessage(Constants.CART_ADD_SUC_KEY));
				}else{
					msgs.add("addCartStatus",new ActionMessage(Constants.CART_ADD_FAIL_KEY));
				}				
				forward = new ActionForward("/cart.do?method=browseCart");
				saveErrors(request, msgs);
			}			
		}catch(Exception ex){
			
			ex.printStackTrace();
		}
		return forward;		
	}
	
	public ActionForward delCart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		CartService cartService = new CartServiceImpl();
		ActionMessages msgs = new ActionMessages();
		Integer id = null;
		if (request.getParameter("id")!=null){
			id = new Integer(request.getParameter("id"));
		}
		try{
		
			Member mem = (Member)request.getSession().getAttribute("member");
			boolean status = false;
			if(mem==null){
				forward = mapping.findForward("memSorry");
			}else{
				if (id!=null){
					status = cartService.delCart(id);
				}
				if (status){
					msgs.add("delCartStatus",new ActionMessage(Constants.CART_DEL_SUC_KEY));
				}else{
					msgs.add("delCartStatus",new ActionMessage(Constants.CART_DEL_FAIL_KEY));
				}				
				forward = new ActionForward("/cart.do?method=browseCart");
				saveErrors(request, msgs);
			}			
		}catch(Exception ex){
			
			ex.printStackTrace();
		}
		return forward;		
	}
	
	public ActionForward clearCart(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		CartService cartService = new CartServiceImpl();
		ActionMessages msgs = new ActionMessages();
		try{
		
			Member mem = (Member)request.getSession().getAttribute("member");
			boolean status = false;
			if(mem==null){
				forward = mapping.findForward("memSorry");
			}else{
				status = cartService.clearCart(mem);
				if (status){
					msgs.add("clearCartStatus",new ActionMessage(Constants.CART_CLEAR_SUC_KEY));
				}else{
					msgs.add("clearCartStatus",new ActionMessage(Constants.CART_CLEAR_FAIL_KEY));
				}				
				forward = new ActionForward("/cart.do?method=browseCart");
				saveErrors(request, msgs);
			}			
		}catch(Exception ex){
			
			ex.printStackTrace();
		}
		return forward;		
	}
	
	public ActionForward checkOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		CartService cartService = new CartServiceImpl();
		try{
			
			Member mem = (Member)request.getSession().getAttribute("member");
			if(mem==null){
				forward = mapping.findForward("memSorry");
			}else{
				forward = mapping.findForward("checkOrder");
			}			
		}catch(Exception ex){
			
			ex.printStackTrace();
		}
		return forward;		
	}
	
	public ActionForward submitOrder(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		String memName = request.getParameter("memName");
		String phone = request.getParameter("phone");
		String zip = request.getParameter("zip");
		String address = request.getParameter("address");
		OrderService oService = new OrderServiceImpl();
		CartService cService = new CartServiceImpl();
		try{
			
			Member mem = (Member)request.getSession().getAttribute("member");
			if(mem==null){
				forward = mapping.findForward("memSorry");
			}else{
				mem.setMemberName(memName.trim());
				mem.setPhone(phone.trim());
				mem.setZip(zip.trim());
				mem.setAddress(address.trim());
				MemService service = new MemServiceImpl();
				
				service.updateMember(mem);
				request.getSession().setAttribute("member",mem);
			
				Orders order = new Orders();
				order.setMember(mem);
				Cart cart = cService.loadCart(mem);
				order.setCart(cart);
				order.setOrderDate(new Date());
				order.setOrderNo(String.valueOf(System.currentTimeMillis()));
				order.setOrderStatus(new Integer(1));
				oService.addOrder(order);
				
				cart.setCartStatus(new Integer(1));
				cService.updateCart(cart);
				request.setAttribute("order", order);
				forward = mapping.findForward("submitOrder");
			}			
		}catch(Exception ex){
		
			ex.printStackTrace();
		}
		return forward;		
	}
}