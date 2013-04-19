/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.hibernate;


import java.util.List;

import model.User;

import exception.DAOException;

/**
 *
 * @author Fogel
 */
public interface UsuarioDAO {

    public List<User> listarUsuario() throws DAOException;
    public void removerUsuario(int id) throws DAOException;
    public void inserirUsuario(int id) throws DAOException;
    public void alterarUsuario(int id, User usuario) throws DAOException;
}
