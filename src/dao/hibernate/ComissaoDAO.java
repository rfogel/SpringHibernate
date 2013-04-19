/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.hibernate;

import model.Commission;
import exception.DAOException;

/**
 *
 * @author Fogel
 */
public interface ComissaoDAO {

    public void salvarComissao(Commission com) throws DAOException;
    public Commission consultarComissao() throws DAOException;
}
