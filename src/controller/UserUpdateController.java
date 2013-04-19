/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.util.ArrayList;
import java.util.List;

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
import org.springframework.web.util.WebUtils;

import validator.ChangePasswordValidator;
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
	public String onSubmit(@ModelAttribute("login") Login log, BindingResult result, HttpServletRequest request) throws Exception
	{
		changePasswordValidator.validate(log, result);
		
		if (result.hasErrors())
			return "userUpdate.jsp";

		if ( WebUtils.hasSubmitParameter(request, "inserir") )
		{
			try
			{
				if (!logBus.consultarLogin(log.getUsername()))
					logBus.inserirLogin(log);
				else
					return "redirect:userUpdate.html?comando=inserir&"
					+ "mensagem=Usuário já existente!";
			}
			catch (BusinessException e)
			{
				e.printStackTrace();
			}

		}

		if ( WebUtils.hasSubmitParameter(request, "editar") )
		{
			try
			{
				String id_login = (String)request.getParameter("id_login");
				String username = (String)request.getParameter("username");
				String password = (String)request.getParameter("password");

				Login login = new Login();
				login.setId(Integer.parseInt(id_login));
				login.setUsername(username);
				login.setPassword(password);

				logBus.editarLogin(login);
			}
			catch (BusinessException e)
			{
				e.printStackTrace();
			}

		}

		return "redirect:userManager.html";
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showUserForm (ModelMap model, HttpServletRequest request)
	{		
		String userId = request.getParameter("userId");
		
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
