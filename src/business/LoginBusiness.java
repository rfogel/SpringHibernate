package business;

import model.Login;
import exception.BusinessException;

public interface LoginBusiness
{
	public void updateLogin(Login login) throws BusinessException;
	public Login getLoginByUsername(String username) throws BusinessException;
}
