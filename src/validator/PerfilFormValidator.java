/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package validator;


import model.User;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author Fogel
 */
public class PerfilFormValidator implements Validator {

      @Override
      public void validate(Object obj, Errors errors)
      {
            User perfil = (User)obj;

            if (perfil.getNome() == null || perfil.getNome().length() == 0 )
                  errors.rejectValue("nome", "error.empty.field", "(Obrigatório)");

            if (perfil.getEmail() == null || perfil.getEmail().length() == 0 )
                  errors.rejectValue("email", "error.empty.field", "(Obrigatório)");

            if (perfil.getTipoProfessor() == null || perfil.getTipoProfessor().length() == 0
                                               || perfil.getTipoProfessor().equals("NONE") )
                  errors.rejectValue("tipoProfessor", "error.empty.field", "(Obrigatório)");

            if (perfil.getNomeProjeto() != null && perfil.getNomeProjeto().length() != 0 )
            {
                  if ( perfil.getValidadeProjetoMes() == null )
                        errors.rejectValue("validadeProjetoMes", "error.empty.field", "(Mês obrigatório)");

                  else if ( perfil.getValidadeProjetoAno() == null )
                        errors.rejectValue("validadeProjetoMes", "error.empty.field", "(Ano obrigatório)");

                  else
                  {
                        if (!((perfil.getValidadeProjetoMes() <= 12) && (perfil.getValidadeProjetoMes() >= 1)))
                              errors.rejectValue("validadeProjetoMes", "error.empty.field", "(Mês inválido)");
                        else if (perfil.getValidadeProjetoAno() < 2011)
                              errors.rejectValue("validadeProjetoMes", "error.empty.field", "(O ano não pode ser menor que 2011)");
                  }
            }
         
            if (perfil.getNomeProjeto() == null || perfil.getNomeProjeto().length() == 0 )
            {
                  if ( perfil.getValidadeProjetoMes() != null || perfil.getValidadeProjetoAno() != null )
                     errors.rejectValue("validadeProjetoMes", "error.empty.field", "(É obrigatório o preenchimento do nome do projeto de pesquisa)");
            }
      }

      @Override
      public boolean supports(@SuppressWarnings("rawtypes") Class clazz)
      {
            return clazz.equals(User.class);
      }
}