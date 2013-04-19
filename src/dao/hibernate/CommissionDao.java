/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dao.hibernate;

import model.Commission;
import exception.DaoException;

/**
 *
 * @author Fogel
 */
public interface CommissionDao 
{
    public void updateCommission(Commission com) throws DaoException;
    public Commission getCommission() throws DaoException;
}
