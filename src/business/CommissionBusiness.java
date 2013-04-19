/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package business;

import model.Commission;
import exception.BusinessException;

/**
 *
 * @author Fogel
 */
public interface CommissionBusiness{

    public void salvarComissao(Commission comissao) throws BusinessException;
    public Commission consultarComissao() throws BusinessException;

}
