/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import javax.servlet.http.HttpServletRequest;

import model.Login;
import model.Role;
import model.User;
import model.UserType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.util.WebUtils;

import utils.SpringUtil;
import validator.ChangePasswordValidator;
import business.BusinessFactory;
import business.BusinessFactoryImpl;
import business.LoginBusiness;
import business.UserBusiness;
import exception.BusinessException;

/**
 *
 * @author Fogel
 */
@Controller
@RequestMapping("userUpdate.html")
@SessionAttributes("login")
public class UserUpdateController 
{
	private ChangePasswordValidator changePasswordValidator;

	@Autowired
	public UserUpdateController(ChangePasswordValidator changePasswordValidator)
	{
		this.changePasswordValidator = changePasswordValidator;
	}

	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(@ModelAttribute("login") Login login, BindingResult result, HttpServletRequest request) throws Exception
	{
		changePasswordValidator.validate(login, result);

		if (result.hasErrors())
			return "userUpdate.jsp";

		LoginBusiness loginBus = BusinessFactory.Instance(BusinessFactoryImpl.class).getLoginBusiness();

		if ( WebUtils.hasSubmitParameter(request, "inserir") )
		{
			try
			{
				Login log = loginBus.getLoginByUsername(login.getUsername());
				
				if ( log == null )
				{
					UserBusiness userBus = BusinessFactory.Instance(BusinessFactoryImpl.class).getUserBusiness();
					
					User user = (User) SpringUtil.getBean("user");
					
					user.setLogin(login);
					user.setName(login.getUsername());
					user.setEmail("");
					user.setProjectName("");
					
					UserType userType = new UserType();
					userType.setUserTypeId(1);
					user.setUserType(userType);
					
					Role role = new Role();				
					role.setRoleId(1);				
					user.setRole(role);
					
					userBus.updateUser(user);
 				}
					
				else
					return "redirect:userUpdate.html?comando=inserir&mensagem=Usuário já existente!";

			} catch (BusinessException e) {
				System.out.println(e.getErrorMessage());
			}
		}

		if ( WebUtils.hasSubmitParameter(request, "editar") )
		{
			try
			{
				String loginId = (String)request.getParameter("loginId");
				String username = (String)request.getParameter("username");
				String password = (String)request.getParameter("password");

				Login log = (Login) SpringUtil.getBean("login");

				log.setLoginId(Integer.parseInt(loginId));
				log.setUsername(username);
				log.setPassword(password);

				loginBus.updateLogin(log);

			} catch (BusinessException e) {
				System.out.println(e.getErrorMessage());
			}
		}

		return "redirect:userManager.html";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showUserForm (ModelMap model, HttpServletRequest request)
	{		
		String userId = request.getParameter("userId");

		if ( userId == null ) 
		{
			Login login = new Login();
			
			model.addAttribute(login);
			
			return "userUpdate.jsp";	
		}
		
		else
		{
			User user = UserManagerController.getUser(Integer.parseInt(userId));
	
			if ( user == null )
			{
				return "userManager.jsp";
			}
	
			user.getLogin().setPassword("");
	
			model.addAttribute(user.getLogin());
	
			return "userUpdate.jsp";	
		}
	}

}
