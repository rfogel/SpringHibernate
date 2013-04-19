/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package business;

import dao.jdbc.ComissaoDAO;
import dao.jdbc.DAOFactory;
import dao.jdbc.DAOFactoryImpl;
import model.Commission;
import exception.BusinessException;
import exception.DaoException;

/**
 *
 * @author Fogel
 */
public class CommissionBusinessBean implements CommissionBusiness {

    public void salvarComissao(Commission comissao) throws BusinessException
    {
            ComissaoDAO comDAO = DAOFactory.getInstance(DAOFactoryImpl.class).getComissaoDAO();
            try
            {
                  comDAO.salvarComissao(comissao);
            } catch (DaoException e) {
                  throw new BusinessException(e.getErrorMessage());
            }
    }

      @Override
      public Commission consultarComissao() throws BusinessException {
            ComissaoDAO comDAO = DAOFactory.getInstance(DAOFactoryImpl.class).getComissaoDAO();
            Commission com = null;
            try
            {
                  com = comDAO.consultarComissao();
            } catch (DaoException e) {
                  throw new BusinessException(e.getErrorMessage());
            }

            return com;
      }

}
