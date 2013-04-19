/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;


import javax.servlet.http.HttpServletRequest;

import model.Order;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.util.WebUtils;

import business.BusinessFactory;
import business.BusinessFactoryImpl;
import business.PedidoBusiness;

import exception.BusinessException;

/**
 *
 * @author Fogel
 */
/*@Controller
@RequestMapping("AvaliarPedido.html")
@SessionAttributes("pedido")*/
public class EvaluateOrderController {

      private PedidoBusiness pedBus;
     
      public EvaluateOrderController()
      {
             this.pedBus = BusinessFactory.Instance(BusinessFactoryImpl.class).getPedidoBusiness();
      }

      @RequestMapping(method = RequestMethod.POST)
      public String onSubmit(@ModelAttribute("pedido") Order pedido, HttpServletRequest request)
                                                                   throws Exception
      {
            String login = (String) request.getSession().getAttribute("username");

            if ( WebUtils.hasSubmitParameter(request, "relatorio") )
            {
                  request.setAttribute("pedido", pedido);
                  return "redirect:Relatorio.html";
            }

            else if ( WebUtils.hasSubmitParameter(request, "remover") )
            {
                  try
                  {
                        pedBus.removerPedido(pedido.getId());

                  }
                  catch (BusinessException e)
                  {
                        e.printStackTrace();
                  }
            }

            else
            {
                  try
                  {
                        boolean decisao = WebUtils.hasSubmitParameter(request, "aceitar");
                        pedBus.avaliarPedido(pedido.getId(), decisao, login);

                  }
                  catch (BusinessException e)
                  {
                        e.printStackTrace();
                  }
            }

            return "redirect:TemplateForm.html";
      }

      @RequestMapping(method = RequestMethod.GET)
      public String showUserForm (ModelMap model, HttpServletRequest request)
      {
            String id = request.getParameter("id");

            Order ped = null;

            try
            {
                  int id_pedido = Integer.parseInt(id);
                  ped = pedBus.consultarPedido(id_pedido);

            } catch ( BusinessException e ){
                  e.printStackTrace();
            }

            model.addAttribute(ped);
            return "AvaliarPedidoForm.jsp";
      }
}
