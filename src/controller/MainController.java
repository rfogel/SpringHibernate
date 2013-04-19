/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Order;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import business.BusinessFactory;
import business.BusinessFactoryImpl;
import business.OrderBusiness;
import exception.BusinessException;

/**
 *
 * @author Fogel
 */
@Controller
@RequestMapping("main.html")
public class MainController
{
	@RequestMapping(method = RequestMethod.GET)
	public String showUserForm (HttpServletRequest request) throws Exception
	{
		OrderBusiness orderBus = BusinessFactory.Instance(BusinessFactoryImpl.class).getOrderBusiness();

		List<Order> lisOrderByUser = null;
		List<Order> lisOrderByUserPendente = null;
		List<Order> lisOrderByUserAvaliado = null;
		List<Order> lisOrderByUserEncerrado = null;
		List<Order> lisOrderByUserEncaminhado = null;
		List<Order> lisOrderByUserEmAprovacao = null;
		List<Order> lisOrderByUserDefinido = null;
		List<Order> lisOrderEmAprovacao = null;
		List<Order> lisOrderDefinidos = null;
		List<Order> lisOrderAvaliado = null;
		List<Order> lisOrderPendente = null;
		List<Order> lisOrderEncaminhado = null;
		List<Order> lisOrderEncerrado = null;
		List<Order> lisOrder = null;

		Integer userId = (Integer) request.getSession().getAttribute("loginId");
		Integer usertypeId = (Integer) request.getSession().getAttribute("usertypeId");
		
		try
		{			
			lisOrderByUser = orderBus.getOrderByUser(userId);
			lisOrder = orderBus.getOrderByUserType(usertypeId);
		}
		catch (BusinessException e)
		{
			System.out.println(e.getErrorMessage());
		}

		lisOrderByUserPendente = new ArrayList<Order>();
		lisOrderByUserAvaliado = new ArrayList<Order>();
		lisOrderByUserEncerrado = new ArrayList<Order>();
		lisOrderByUserEncaminhado = new ArrayList<Order>();
		lisOrderByUserEmAprovacao = new ArrayList<Order>();
		lisOrderByUserDefinido = new ArrayList<Order>();

		if ( lisOrderByUser != null )
		{
			for ( Order p : lisOrderByUser )
			{
				if (p.getStatus().getStatusId() == 1)
					lisOrderByUserPendente.add(p);
				else if (p.getStatus().getStatusId() == 2)
					lisOrderByUserAvaliado.add(p);
				else if (p.getStatus().getStatusId() == 3)
					lisOrderByUserEncaminhado.add(p);
				else if (p.getStatus().getStatusId() == 4)
					lisOrderByUserEncerrado.add(p);
				else if (p.getStatus().getStatusId() == 9 || p.getStatus().getStatusId() == 12)
					lisOrderByUserDefinido.add(p);
				else
					lisOrderByUserEmAprovacao.add(p);
			}
		}
		
		if ( lisOrder != null )
		{
			switch ( usertypeId )
	        {
	              case 2:
	              case 3:
	                    lisOrderEmAprovacao = new ArrayList<Order>();
	                    lisOrderAvaliado = new ArrayList<Order>();
	                    lisOrderDefinidos = new ArrayList<Order>();
	                    
	                    for ( Order p : lisOrder )
	                    {
	                          if ( p.getStatus().getStatusId() == 2 )
	                                lisOrderAvaliado.add(p);
	                          else if ((p.getStatus().getStatusId() == 9) || (p.getStatus().getStatusId() == 12))
	                                lisOrderDefinidos.add(p);
	                          else if (p.getStatus().getStatusId() > 4)
	                                lisOrderEmAprovacao.add(p);
	                    }
	                    
	                    break;
	                    
	              case 4:           	  
	                    lisOrderPendente = new ArrayList<Order>();
	                    lisOrderAvaliado = new ArrayList<Order>();
	                    lisOrderEncaminhado = new ArrayList<Order>();
	                    lisOrderEncerrado = new ArrayList<Order>();
	                    lisOrderEmAprovacao = new ArrayList<Order>();
	                    lisOrderDefinidos = new ArrayList<Order>();
	
	                    for ( Order p : lisOrder )
	                    {
	                          if (p.getStatus().getStatusId() == 1)
	                                lisOrderPendente.add(p);
	                          else if (p.getStatus().getStatusId() == 2)
	                                lisOrderAvaliado.add(p);
	                          else if (p.getStatus().getStatusId() == 3)
	                                lisOrderEncaminhado.add(p);
	                          else if (p.getStatus().getStatusId() == 4)
	                                lisOrderEncerrado.add(p);
	                          else if (p.getStatus().getStatusId() == 9 || p.getStatus().getStatusId() == 12)
	                                lisOrderDefinidos.add(p);
	                          else
	                                lisOrderEmAprovacao.add(p);
	                    }
	                    
	                    break;
	        }
		}

		if ( lisOrderByUser != null ) request.setAttribute("cotaEsgotada", (lisOrderByUser.size() == 3));

		if ( usertypeId != 1 )
		{
			request.setAttribute("listOrderComissaoVazia", (lisOrderAvaliado.isEmpty() && lisOrderDefinidos.isEmpty() && lisOrderEmAprovacao.isEmpty()));
			request.setAttribute("listOrderPresidenteVazia", (lisOrder == null || lisOrder.isEmpty()));
		}

		request.setAttribute("listOrderByUser", lisOrderByUser);

		request.setAttribute("listOrderByUserPendente", lisOrderByUserPendente);
		request.setAttribute("listOrderByUserAvaliado", lisOrderByUserAvaliado);
		request.setAttribute("listOrderByUserEncaminhado", lisOrderByUserEncaminhado);
		request.setAttribute("listOrderByUserEncerrado", lisOrderByUserEncerrado);
		request.setAttribute("listOrderByUserEmAprovacao", lisOrderByUserEmAprovacao);
		request.setAttribute("listOrderByUserDefinido", lisOrderByUserDefinido);

		request.setAttribute("listOrderEmAprovacao", lisOrderEmAprovacao);
		request.setAttribute("listOrderDefinidos", lisOrderDefinidos);
		request.setAttribute("listOrderPendente", lisOrderPendente);
		request.setAttribute("listOrderEncaminhado", lisOrderEncaminhado);
		request.setAttribute("listOrderAvaliado", lisOrderAvaliado);
		request.setAttribute("listOrderEncerrado", lisOrderEncerrado);

		return "main.jsp";
	}
}
