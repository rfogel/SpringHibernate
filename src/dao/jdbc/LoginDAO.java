/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.jdbc;


import java.util.List;

import model.Login;

import exception.DaoException;

/**
 *
 * @author Fogel
 */
public interface LoginDAO {

    public Login logar(Login login) throws DaoException;
    public List<Login> listarLogin() throws DaoException;
    public void inserirLogin(Login login) throws DaoException;
    public void removerLogin(int id) throws DaoException;
    public void editarLogin(Login login) throws DaoException;
    public Login consultarLogin(int id) throws DaoException;
    public boolean consultarLogin(String username) throws DaoException;
}
