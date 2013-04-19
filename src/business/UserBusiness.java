/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package business;


import java.util.List;

import model.Login;
import model.Role;
import model.User;
import exception.BusinessException;

/**
 *
 * @author Fogel
 */
public interface UserBusiness
{
    public void updateUser(User user) throws BusinessException;
    public void deleteUser(User user) throws BusinessException;
    public void deleteUserById(Integer userId) throws BusinessException;
    public User getUserById(Integer userid) throws BusinessException;
    public User getUserByLogin(Login login) throws BusinessException;
    public List<User> getUser() throws BusinessException;
    public List<Role> getRole() throws BusinessException;
}
