/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.jdbc;


import java.util.List;

import model.User;

import exception.DaoException;

/**
 *
 * @author Fogel
 */
public interface UsuarioDAO {

    public List<User> listarUsuario() throws DaoException;
    public void removerUsuario(int id) throws DaoException;
    public void inserirUsuario(int id) throws DaoException;
    public void alterarUsuario(int id, User usuario) throws DaoException;
}
