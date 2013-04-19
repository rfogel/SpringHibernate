/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.jdbc;

import model.Commission;
import exception.DaoException;

/**
 *
 * @author Fogel
 */
public interface ComissaoDAO {

    public void salvarComissao(Commission com) throws DaoException;
    public Commission consultarComissao() throws DaoException;
}
