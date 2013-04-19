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
public interface PerfilDAO {

    public void atualizarPerfil(User perfil) throws DAOException;
    public User consultarPerfil(int id) throws DAOException;
    public List<String> listarTipoProfessor() throws DAOException;
    public List<User> listarPerfil() throws DAOException;
}
