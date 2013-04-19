/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.hibernate;


import java.util.List;

import model.Login;
import model.Role;
import model.User;
import exception.DaoException;

/**
 *
 * @author Fogel
 */
public interface UserDao 
{
    public void updateUser(User user) throws DaoException;
    public void deleteUser(User user) throws DaoException;
    public void deleteUserById(Integer userId) throws DaoException;
    public User getUserById(Integer userId) throws DaoException;
    public User getUserByLogin(Login login) throws DaoException;
    public List<User> getUser() throws DaoException;
    public List<Role> getRole() throws DaoException;
}
