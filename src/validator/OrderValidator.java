/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package validator;


import model.Order;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author Fogel
 */
public class OrderValidator implements Validator 
{

	@Override
	public void validate(Object obj, Errors errors)
	{
		Order pedido = (Order)obj;

		if (pedido.getQuota() == null || pedido.getQuota().getName().length() == 0 || pedido.getQuota().equals("NONE"))
			errors.rejectValue("cota", "error.empty.field", "(Obrigatório)");

		if (pedido.getName() == null || pedido.getName().length() == 0)
			errors.rejectValue("nomeArtigo", "error.empty.field", "(Obrigatório)");

		//ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nomeArtigo", "campo_vazio", null, "(Obrigatório)");

		if (pedido.getConferenceName() == null || pedido.getConferenceName().length() == 0)
			errors.rejectValue("nomeConferencia", "error.empty.field", "(Obrigatório)");

		if (pedido.getFinancing().getName() == null || pedido.getFinancing().getName().length() == 0 ||
				pedido.getFinancing().getName().equals("NONE"))
			errors.rejectValue("financiamento", "error.empty.field", "(Obrigatório)");

		if ( pedido.getNumberOfArticles() == null )
			errors.rejectValue("numeroArtigos", "error.empty.field", "(Obrigatório)");

		else
		{
			if (pedido.getNumberOfArticles() == 0)
				errors.rejectValue("numeroArtigos", "error.empty.field", "(Obrigatório)");
		}

		if (pedido.getQualification() == null || pedido.getQualification().getType().length() == 0
				|| pedido.getQualification().getType().equals("NONE"))
			errors.rejectValue("qualificacaoConferencia", "error.empty.field", "(Obrigatório)");
	}

	@Override
	public boolean supports(@SuppressWarnings("rawtypes") Class clazz)
	{
		return clazz.equals(Order.class);
	}
}