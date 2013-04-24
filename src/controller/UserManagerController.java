/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Login;
import model.Order;
import model.User;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.util.WebUtils;

import business.BusinessFactory;
import business.BusinessFactoryImpl;
import business.OrderBusiness;
import business.UserBusiness;
import exception.BusinessException;

/**
 *
 * @author Fogel
 */
@Controller
@RequestMapping("userManager.html")
@SessionAttributes("login")
public class UserManagerController 
{
	private static List<User> users;

	public static User getUser(Integer userId) 
	{ 
		for ( User u : users )
			if ( u.getUserId().equals(userId) )
				return u;

		return null;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(@ModelAttribute("login") Login log, HttpServletRequest request) throws Exception
	{
		UserBusiness userBus = BusinessFactory.Instance(BusinessFactoryImpl.class).getUserBusiness();
		OrderBusiness orderBus = BusinessFactory.Instance(BusinessFactoryImpl.class).getOrderBusiness();

		if ( WebUtils.hasSubmitParameter(request, "remover") )
		{
			try
			{
				String loginId = request.getParameter("loginId");

				List<Order> orders = orderBus.getOrderByUser(Integer.parseInt(loginId));

				if ( orders != null && !orders.isEmpty() )
					return "redirect:userManager.html?status=erro&mensagem=O usuário possui pedidos no sistema e não é possível excluir-lo!";

				User user = getUser(Integer.parseInt(loginId));
				
				userBus.deleteUser(user);

			} catch (BusinessException e) {
				System.out.println(e.getErrorMessage());
			}

		}

		return "redirect:userManager.html?status=ok&mensagem=Usuário excluído com sucesso!";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showUserForm (ModelMap model, HttpServletRequest request)
	{
		users = null;

		UserBusiness userBus = BusinessFactory.Instance(BusinessFactoryImpl.class).getUserBusiness();

		try
		{
			users = userBus.getUser();
			request.setAttribute("listaUser", users);

		} catch ( BusinessException e ){
			System.out.println(e.getErrorMessage());
		}

		return "userManager.jsp";
	}
}
