/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import model.Commission;
import model.Login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import validator.ConfigurationValidator;

import business.BusinessFactory;
import business.BusinessFactoryImpl;
import business.CommissionBusiness;
import business.LoginBusiness;

import exception.BusinessException;

/**
 *
 * @author Fogel
 */
/*@Controller
@RequestMapping("Configuracao.html")
@SessionAttributes("comissao")*/
public class ConfigurationController {

      private CommissionBusiness comBus;
      private ConfigurationValidator configuracaoFormValidator;
      private LoginBusiness logBus;

      @Autowired
      public ConfigurationController(ConfigurationValidator configuracaoFormValidator)
      {
            this.configuracaoFormValidator = configuracaoFormValidator;
            this.comBus = BusinessFactory.Instance(BusinessFactoryImpl.class).getComissaoBusiness();
            this.logBus = BusinessFactory.Instance(BusinessFactoryImpl.class).getLoginBusiness();
      }

      @RequestMapping(method = RequestMethod.POST)
      public String onSubmit(@ModelAttribute("comissao") Commission comissao, BindingResult result,
                             HttpServletRequest request) throws Exception
      {
            configuracaoFormValidator.validate(comissao, result);
            if (result.hasErrors())
                  return "ConfiguracaoForm.jsp";

            Commission com = new Commission();

            com.setLoginPresidente(comissao.getLoginPresidente());
            com.setLoginPrimeiroMembro(comissao.getLoginPrimeiroMembro());
            com.setLoginSegundoMembro(comissao.getLoginSegundoMembro());

            try
            {
                  comBus.salvarComissao(comissao);
            }
            catch (BusinessException e)
            {
                  e.printStackTrace();
            }

            return "redirect:TemplateForm.html";
      }

      @RequestMapping(method = RequestMethod.GET)
      public String showUserForm (ModelMap model, HttpServletRequest request)
      {
            Commission comissao = new Commission();
            try
            {
                  comissao = comBus.consultarComissao();
            }
            catch (BusinessException e)
            {
                  e.printStackTrace();
            }

            model.addAttribute(comissao);
            return "ConfiguracaoForm.jsp";
      }

      @ModelAttribute("listaLogin")
      public List<String> populaListaLogin(HttpServletRequest request) throws Exception
      {
            List<Login> lisLog = null;
            List<String> listaLogin = null;

            try
            {
                  lisLog = logBus.listarLogin();

            } catch ( BusinessException e ){
                  e.printStackTrace();
            }

            listaLogin = new ArrayList<String>();
            for ( Login log : lisLog )
                  listaLogin.add(log.getUsername());

            return listaLogin;
     }
}
