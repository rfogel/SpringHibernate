/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package validator;


import model.Login;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author Fogel
 */
public class ChangePasswordValidator implements Validator 
{
	@Override
	public void validate(Object obj, Errors errors)
	{
		Login login = (Login)obj;

		if (login.getUsername() == null || login.getUsername().length() == 0)
			errors.rejectValue("username", "error.empty.field", "(Obrigatório)");

		if (login.getPassword() == null || login.getPassword().length() == 0)
			errors.rejectValue("password", "error.empty.field", "(Obrigatório)");

		if (login.getNewPassword() == null || login.getNewPassword().length() == 0)
			errors.rejectValue("newPassword", "error.empty.field", "(Obrigatório)");

		else 
		{
			if (!login.getNewPassword().equals(login.getPassword()))
				errors.rejectValue("newPassword", "error.empty.field", "(As senhas nã são iguais!)");
		}

	}

	@Override
	public boolean supports(@SuppressWarnings("rawtypes") Class clazz)
	{
		return clazz.equals(Login.class);
	}
}