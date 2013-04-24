/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Role;
import model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import validator.UserProfileValidator;
import business.BusinessFactory;
import business.BusinessFactoryImpl;
import business.UserBusiness;
import exception.BusinessException;

/**
 *
 * @author Fogel
 */

@Controller
@RequestMapping("userProfile.html")
@SessionAttributes("user")
public class UserProfileController 
{
	private UserProfileValidator userProfileValidator;

	@Autowired
	public UserProfileController(UserProfileValidator userProfileValidator)
	{
		this.userProfileValidator = userProfileValidator;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(@ModelAttribute("user") User user, BindingResult result, HttpServletRequest request) throws Exception
	{
		userProfileValidator.validate(user, result);

		if (result.hasErrors())
			return "userProfile.jsp";

		UserBusiness userBus = BusinessFactory.Instance(BusinessFactoryImpl.class).getUserBusiness();

		try
		{
			userBus.updateUser(user);

		} catch (BusinessException e) {
			System.out.println(e.getErrorMessage());
		}

		return "redirect:main.html";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showUserForm (ModelMap model, HttpServletRequest request)
	{
		User user = (User) request.getSession().getAttribute("user");

		model.addAttribute(user);
		
		return "userProfile.jsp";
	}

	@ModelAttribute("listaTipoProfessor")
	public List<Role> populaTipoProfessor(HttpServletRequest request) throws Exception
	{
		List<Role> lisRoles = new ArrayList<Role>();
		
		UserBusiness userBus = BusinessFactory.Instance(BusinessFactoryImpl.class).getUserBusiness();
		
		try
		{
			lisRoles = userBus.getRole();
		
		} catch (BusinessException e) {
			System.out.println(e.getErrorMessage());
		}

		return lisRoles;
	}
	
	@InitBinder
    public void initBinder(WebDataBinder binder) 
	{
        CustomDateEditor editor = new CustomDateEditor(new SimpleDateFormat("dd/MM/YYYY"), true);
        binder.registerCustomEditor(Date.class, editor);
    }
}
