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

import validator.ChangePasswordValidator;
import business.BusinessFactory;
import business.BusinessFactoryImpl;
import business.UserBusiness;
import exception.BusinessException;

/**
 *
 * @author Fogel
 */
@Controller
@RequestMapping("changePassword.html")
@SessionAttributes("login")
public class ChangePasswordController {

	private ChangePasswordValidator changePasswordValidator;

	@Autowired
	public ChangePasswordController(ChangePasswordValidator changePasswordValidator)
	{
		this.changePasswordValidator = changePasswordValidator;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(@ModelAttribute("login") Login login, BindingResult result, HttpServletRequest request) throws Exception
	{
		changePasswordValidator.validate(login, result);
		
		if (result.hasErrors())
			return "changePassword.jsp";

		UserBusiness userBus = BusinessFactory.Instance(BusinessFactoryImpl.class).getUserBusiness();
		
		User user = (User)request.getSession().getAttribute("user");
		
		user.setLogin(login);

		try
		{
			userBus.updateUser(user);
		}
		catch (BusinessException e)
		{
			System.out.println(e.getErrorMessage());
		}

		return "redirect:main.html";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showUserForm (ModelMap model, HttpServletRequest request)
	{
		Login login = ((User)request.getSession().getAttribute("user")).getLogin();

		model.addAttribute(login);

		return "changePassword.jsp";
	}

}
