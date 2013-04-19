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
public interface PerfilDAO {

    public void atualizarPerfil(User perfil) throws DaoException;
    public User consultarPerfil(int id) throws DaoException;
    public List<String> listarTipoProfessor() throws DaoException;
    public List<User> listarPerfil() throws DaoException;
}
