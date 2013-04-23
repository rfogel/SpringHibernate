package business;

import model.Login;
import dao.hibernate.DaoFactory;
import dao.hibernate.DaoFactoryImpl;
import dao.hibernate.LoginDao;
import exception.BusinessException;
import exception.DaoException;

public class LoginBusinessImpl implements LoginBusiness
{
	@Override
	public void updateLogin(Login login) throws BusinessException
	{
		LoginDao loginDao = DaoFactory.getInstance(DaoFactoryImpl.class).getLoginDao();

		try
		{
			loginDao.updateLogin(login);
		}
		catch (DaoException e) {
			throw new BusinessException(e.getErrorMessage());
		}
	}

	@Override
	public Login getLoginByUsername(String username) throws BusinessException
	{
		LoginDao loginDao = DaoFactory.getInstance(DaoFactoryImpl.class).getLoginDao();

		Login login = null;
		
		try
		{
			login = loginDao.getLoginByUsername(username);
		}
		catch (DaoException e) {
			throw new BusinessException(e.getErrorMessage());
		}	
		
		return login;
	}
}
