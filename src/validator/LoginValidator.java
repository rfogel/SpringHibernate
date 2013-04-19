/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package validator;


import model.Login;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author Fogel
 */
public class LoginValidator implements Validator {

      @Override
      public void validate(Object obj, Errors errors)
      {
            Login login = (Login)obj;
            
            if (login.getUsername() == null || login.getUsername().length() == 0)
                  errors.rejectValue("username", "error.empty.field", "(Obrigatório)");

            if (login.getPassword() == null || login.getPassword().length() == 0)
                  errors.rejectValue("password", "error.empty.field", "(Obrigatório)");
      }

      @Override
      public boolean supports(@SuppressWarnings("rawtypes") Class clazz)
      {
            return clazz.equals(Login.class);
      }
}