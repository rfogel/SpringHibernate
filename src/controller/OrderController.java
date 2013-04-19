/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import model.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import utils.SpringUtil;
import validator.OrderValidator;
import business.BusinessFactory;
import business.BusinessFactoryImpl;
import business.OrderBusiness;
import exception.BusinessException;

/**
 *
 * @author Fogel
 */
@Controller
@RequestMapping("order.html")
@SessionAttributes("order")
public class OrderController {

	private OrderValidator orderFormValidator;
	private OrderBusiness orderBus;

	@Autowired
	public OrderController(OrderValidator orderFormValidator)
	{
		this.orderFormValidator = orderFormValidator;
		orderBus = BusinessFactory.Instance(BusinessFactoryImpl.class).getOrderBusiness();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String showUserForm (ModelMap model)
	{
		Order order = (Order) SpringUtil.getBean("order");
		
		order.setNumberOfArticles(1);
		
		model.addAttribute(order);
		
		return "order.jsp";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String onSubmit(@ModelAttribute("order") Order order, BindingResult result,
			HttpServletRequest request) throws Exception
			{
		orderFormValidator.validate(order, result);
		if (result.hasErrors())
			return "orderForm.jsp";

		Order ped = new Order();

		String login = (String) request.getSession().getAttribute("username");

		ped.setAutor(login);
		ped.setCota(order.getCota());
		ped.setFinanciamento(order.getFinanciamento());
		ped.setNomeArtigo(order.getNomeArtigo());
		ped.setNomeConferencia(order.getNomeConferencia());
		ped.setNumeroArtigos(order.getNumeroArtigos());
		ped.setQualificacaoConferencia(order.getQualificacaoConferencia());

		OrderBusiness orderBus = BusinessFactory.Instance(BusinessFactoryImpl.class).getOrderBusiness();

		try
		{
			orderBus.inserirorder(ped);
		}
		catch (BusinessException e)
		{
			e.printStackTrace();
		}

		return "redirect:TemplateForm.html";
			}

	@ModelAttribute("listaCota")
	public List<String> populaCota(HttpServletRequest request) throws Exception
	{
		String login = (String) request.getSession().getAttribute("username");
		List<String> cota = orderBus.listarCota(login);
		return cota;
	}

	@ModelAttribute("listaQualificacao")
	public List<String> populaQualificacao(HttpServletRequest request) throws Exception
	{
		String login = (String) request.getSession().getAttribute("username");
		List<String> qualificacao = orderBus.listarQualificacao();
		return qualificacao;
	}

	@ModelAttribute("listaFinanciamento")
	public List<String> populaFinanciamento(HttpServletRequest request) throws Exception
	{
		String login = (String) request.getSession().getAttribute("username");
		List<String> financiamento = orderBus.listarFinanciamento();
		return financiamento;
	}
}
