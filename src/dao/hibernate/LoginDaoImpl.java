package dao.hibernate;

import model.Login;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import exception.DaoException;
import exception.MessageException;

public class LoginDaoImpl extends Dao implements LoginDao
{
	@Override
	public void updateLogin(Login login) throws DaoException
	{
		Session session = null;
		
		try
		{
			session = getSessionFactory().openSession();
			saveOrUpdate(login);
			session.close();
			
		} catch (HibernateException e) {
			session.close();
			throw new DaoException(MessageException.DAO_EXCEPTION + ": " + e.getMessage());
		}
	}

	@Override
	public Login getLoginByUsername(String username) throws DaoException
	{
		Session session = null;
		
		Login result = null;
		
		try
		{
			session = getSessionFactory().openSession();
			Query query = session.createQuery("from Login where username = '" + username + "'");
			result = (Login) query.uniqueResult();
			session.close();
			
		} catch (HibernateException e) {
			session.close();
			throw new DaoException(MessageException.DAO_EXCEPTION + ": " + e.getMessage());
		}
		
		return result;
	}

}
