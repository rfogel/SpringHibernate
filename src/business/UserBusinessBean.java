/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package business;


import java.util.List;

import model.Login;
import model.Role;
import model.User;
import dao.hibernate.DaoFactory;
import dao.hibernate.DaoFactoryImpl;
import dao.hibernate.UserDao;
import exception.BusinessException;
import exception.DaoException;

/**
 *
 * @author Fogel
 */
public class UserBusinessBean implements UserBusiness 
{

	@Override
	public void updateUser(User user) throws BusinessException 
	{
		UserDao userDao = DaoFactory.getInstance(DaoFactoryImpl.class).getUserDao();

		try {
			userDao.updateUser(user);
		} catch (DaoException e) {
			throw new BusinessException(e.getErrorMessage());
		}
	}
	
	@Override
	public void deleteUser(User user) throws BusinessException 
	{
		UserDao userDao = DaoFactory.getInstance(DaoFactoryImpl.class).getUserDao();

		try {
			userDao.deleteUser(user);
		} catch (DaoException e) {
			throw new BusinessException(e.getErrorMessage());
		}	
	}
	
	@Override
	public void deleteUserById(Integer userId) throws BusinessException 
	{
		UserDao userDao = DaoFactory.getInstance(DaoFactoryImpl.class).getUserDao();

		try {
			userDao.deleteUserById(userId);
		} catch (DaoException e) {
			throw new BusinessException(e.getErrorMessage());
		}	
	}

	@Override
	public User getUserById(Integer userId) throws BusinessException {
		return null;
	}

	@Override
	public User getUserByLogin(Login login) throws BusinessException 
	{
		UserDao userDao = DaoFactory.getInstance(DaoFactoryImpl.class).getUserDao();
		
		User user = null;

		try {
			user = userDao.getUserByLogin(login);
		} catch (DaoException e) {
			throw new BusinessException(e.getErrorMessage());
		}

		return user;
	}

	@Override
	public List<User> getUser() throws BusinessException 
	{
		UserDao userDao = DaoFactory.getInstance(DaoFactoryImpl.class).getUserDao();
		
		List<User> users = null;

		try {
			users = userDao.getUser();
		} catch (DaoException e) {
			throw new BusinessException(e.getErrorMessage());
		}

		return users;
	}

	@Override
	public List<Role> getRole() throws BusinessException 
	{
		UserDao userDao = DaoFactory.getInstance(DaoFactoryImpl.class).getUserDao();
		
		List<Role> roles = null;

		try {
			roles = userDao.getRole();
		} catch (DaoException e) {
			throw new BusinessException(e.getErrorMessage());
		}

		return roles;
	}
}
