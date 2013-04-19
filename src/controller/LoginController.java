/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import javax.servlet.http.HttpServletRequest;

import model.Login;
import model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import utils.SpringUtil;
import validator.LoginValidator;
import business.BusinessFactory;
import business.BusinessFactoryImpl;
import business.UserBusiness;
import exception.BusinessException;

/**
 *
 * @author Fogel
 */

@Controller
@RequestMapping("login.html")
@SessionAttributes("login")
public class LoginController 
{
	private LoginValidator loginValidator;

	@Autowired
	public LoginController(LoginValidator loginValidator)
	{
		this.loginValidator = loginValidator;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showUserForm (ModelMap model)
	{
		Login login = (Login) SpringUtil.getBean("login");
		
		login.setUsername("rfogel");
		login.setPassword("fogel");
		
		model.addAttribute(login);
		
		return "login.jsp";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(@ModelAttribute("login") Login login, BindingResult result, HttpServletRequest request) throws Exception
	{
		loginValidator.validate(login, result);

		if (result.hasErrors())
			return "login.jsp";

		UserBusiness userBus = BusinessFactory.Instance(BusinessFactoryImpl.class).getUserBusiness();

		try
		{
			User user = userBus.getUserByLogin(login);

			if ( user == null )
			{
				return "redirect:login.html?mensagem=Usuário ou senha incorreta!";
			}
			else
			{
				request.getSession().setAttribute("user", user);
				request.getSession().setAttribute("username", user.getName());
				request.getSession().setAttribute("loginId", user.getUserId());
				request.getSession().setAttribute("usertypeId", user.getUserType().getUserTypeId());
			}
		}
		catch (BusinessException e)
		{
			System.out.println(e.getMessage());
		}

		return "redirect:main.html";
	}
}
