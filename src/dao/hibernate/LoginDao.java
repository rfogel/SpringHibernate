package dao.hibernate;

import model.Login;
import exception.DaoException;

public interface LoginDao
{
	public void updateLogin(Login login) throws DaoException;
	public Login getLoginByUsername(String username) throws DaoException;
}
