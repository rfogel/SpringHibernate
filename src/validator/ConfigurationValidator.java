/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package validator;


import model.Commission;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author Fogel
 */
public class ConfigurationValidator implements Validator {

      @Override
      public void validate(Object obj, Errors errors)
      {
            Commission comissao = (Commission)obj;

            if (comissao.getPresident().getName() == null || comissao.getPresident().getName().length() == 0 || comissao.getPresident().getName().equals("NONE"))
                  errors.rejectValue("loginPresidente", "error.empty.field", "(Obrigatório)");

            else if (comissao.getPresident().getName().equals(comissao.getFirstMember().getName()))
                  errors.rejectValue("loginPresidente", "error.empty.field", "(Não é possível definir o "
                          + "mesmo login para mais de um cargo!)");
            else if (comissao.getPresident().getName().equals(comissao.getSecondMember().getName()))
                  errors.rejectValue("loginPresidente", "error.empty.field", "(Não é possível definir o "
                          + "mesmo login para mais de um cargo!)");

            if (comissao.getFirstMember().getName() == null || comissao.getFirstMember().getName().length() == 0
                                                          || comissao.getFirstMember().getName().equals("NONE"))
                  errors.rejectValue("loginPrimeiroMembro", "error.empty.field", "(Obrigatório)");

            else if (comissao.getFirstMember().getName().equals(comissao.getSecondMember().getName()))
                  errors.rejectValue("loginPrimeiroMembro", "error.empty.field", "(Não é possível definir o "
                          + "mesmo login para mais de um cargo!)");

            if (comissao.getSecondMember().getName() == null || comissao.getSecondMember().getName().length() == 0
                                                         || comissao.getSecondMember().getName().equals("NONE"))
                  errors.rejectValue("loginSegundoMembro", "error.empty.field", "(Obrigatório)");      
      }

      @Override
      public boolean supports(@SuppressWarnings("rawtypes") Class clazz)
      {
            return clazz.equals(Commission.class);
      }
}