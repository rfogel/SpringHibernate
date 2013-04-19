/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.hibernate;


import java.util.List;

import model.Login;
import model.Role;
import model.User;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import exception.DaoException;
import exception.MessageException;

/**
 *
 * @author Fogel
 */
@SuppressWarnings("unchecked")
public class UserDaoImpl extends Dao implements UserDao
{
	@Override
	public void updateUser(User user) throws DaoException 
	{	
		Session session = null;
		
		try
		{
			session = getSessionFactory().openSession();
			saveOrUpdate(user);
			
		} catch (HibernateException e) {
			session.close();
			throw new DaoException(MessageException.DAO_EXCEPTION + ": " + e.getMessage());
		}
	}
	
	@Override
	public void deleteUser(User user) throws DaoException 
	{
		Session session = null;
		
		try
		{
			session = getSessionFactory().openSession();
			delete(user);
			
		} catch (HibernateException e) {
			session.close();
			throw new DaoException(MessageException.DAO_EXCEPTION + ": " + e.getMessage());
		}		
	}

	@Override
	public User getUserById(Integer loginId) throws DaoException 
	{
		return null;
	}

	@Override
	public User getUserByLogin(Login login) throws DaoException 
	{
		Session session = null;
		User result = null;
		
		try
		{
			session = getSessionFactory().openSession();
			Query query = session.createQuery("from User where login.username = '" + login.getUsername() + "' and login.password = '" + login.getPassword() + "'");
			result = (User) query.uniqueResult();
			session.close();
			
		} catch (HibernateException e) {
			session.close();
			throw new DaoException(MessageException.DAO_EXCEPTION + ": " + e.getMessage());
		}

		return result;
	}

	@Override
	public List<User> getUser() throws DaoException 
	{
		Session session = null;
		List<User> result = null;
		
		try
		{
			session = getSessionFactory().openSession();
			Query query = session.createQuery("from User");
			result = query.list();
			session.close();
			
		} catch (HibernateException e) {
			session.close();
			throw new DaoException(MessageException.DAO_EXCEPTION + ": " + e.getMessage());
		}

		return result;
	}

	@Override
	public List<Role> getRole() throws DaoException 
	{
		Session session = null;
		List<Role> result = null;
		
		try
		{
			session = getSessionFactory().openSession();
			Query query = session.createQuery("from Role");
			result = query.list();
			session.close();
			
		} catch (HibernateException e) {
			session.close();
			throw new DaoException(MessageException.DAO_EXCEPTION + ": " + e.getMessage());
		}

		return result;
	}

	@Override
	public void deleteUserById(Integer userId) throws DaoException 
	{
		Session session = null;
		
		try
		{
			session = getSessionFactory().openSession();
			Query query = session.createQuery("delete from User where userId = '" + userId + "'");
			query.executeUpdate();
			session.close();
			
		} catch (HibernateException e) {
			session.close();
			throw new DaoException(MessageException.DAO_EXCEPTION + ": " + e.getMessage());
		}		
	}
}
