/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package validator;


import model.User;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author Fogel
 */
@SuppressWarnings("deprecation")
public class UserProfileValidator implements Validator 
{
	
	@Override
	public void validate(Object obj, Errors errors)
	{
		User user = (User)obj;

		if (user.getName() == null || user.getName().length() == 0 )
			errors.rejectValue("name", "error.empty.field", "(Obrigat�rio)");

		if (user.getEmail() == null || user.getEmail().length() == 0 )
			errors.rejectValue("email", "error.empty.field", "(Obrigat�rio)");

		if (user.getRole() == null || user.getRole().getName().length() == 0 || user.getRole().getName().contains("Selecione"))
			errors.rejectValue("role.roleId", "error.empty.field", "(Obrigat�rio)");

		if (user.getProjectName() != null && user.getProjectName().length() != 0 )
		{
			if ( user.getProjectExpireDate() == null )
				errors.rejectValue("projectExpireDate", "error.empty.field", "(Validade do projeto obrigat�ria)");

			else
			{
				if (!((user.getProjectExpireDate().getDay() <= 31) && (user.getProjectExpireDate().getDay() >= 1)))
					errors.rejectValue("projectExpireDate", "error.empty.field", "(Dia inv�lido)");
				
				else if (!((user.getProjectExpireDate().getMonth() <= 12) && (user.getProjectExpireDate().getMonth() >= 1)))
					errors.rejectValue("projectExpireDate", "error.empty.field", "(M�s inv�lido)");
				
				else if (user.getProjectExpireDate().getYear() < 2013)
					errors.rejectValue("projectExpireDate", "error.empty.field", "(O ano n�o pode ser menor que 2013)");
			}
		}

		if (user.getProjectName() == null || user.getProjectName().length() == 0 )
		{
			if ( user.getProjectExpireDate() != null )
				errors.rejectValue("projectExpireDate", "error.empty.field", "(� obrigat�rio o preenchimento do nome do projeto de pesquisa)");
		}
	}

	@Override
	public boolean supports(@SuppressWarnings("rawtypes") Class clazz)
	{
		return clazz.equals(User.class);
	}
}