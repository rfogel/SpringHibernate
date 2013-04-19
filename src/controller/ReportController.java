/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;


import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;

import model.Order;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Fogel
 */
/*@Controller
@RequestMapping("Relatorio.html")*/
public class ReportController {

      @RequestMapping(method = RequestMethod.GET)
      public String showUserForm (HttpServletRequest request)
      {
            Order pedido = (Order)request.getSession().getAttribute("pedido");

            request.setAttribute("autor", pedido.getAutor());
            request.setAttribute("qualificacao", pedido.getQualificacaoConferencia());
            request.setAttribute("nomeConferencia", pedido.getNomeConferencia());
            request.setAttribute("nomeArtigo", pedido.getNomeArtigo());
            request.setAttribute("financiamento", pedido.getFinanciamento());
            request.setAttribute("cota", pedido.getCota());
            request.setAttribute("numeroArtigos", pedido.getNumeroArtigos());

            Date data = new Date();
            SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy");
            String dataFormatada = formatador.format(data);
            request.setAttribute("dataHora", dataFormatada);

            int pontos = CalculaPontos(pedido);
            
            String conceito = CalculaConceito(pontos);

            int valorSugerido = CalculaFinanciamentoSugerido(conceito, pedido.getCota());

            String moeda = ( pedido.getCota().equals("Cota Internacional") ? "U$" : "R$" );

            request.setAttribute("conceito", conceito);
            request.setAttribute("valorSugerido", valorSugerido);
            request.setAttribute("moeda", moeda);

            return "RelatorioForm.jsp";
      }

      public int CalculaPontos(Order pedido)
      {
            int pontos = 0;

            if ( pedido.getQualificacaoConferencia().equals("A1") )
                  pontos = 20;
            else if ( pedido.getQualificacaoConferencia().equals("A2"))
                  pontos = 18;
            else if ( pedido.getQualificacaoConferencia().equals("B1"))
                  pontos = 16;
            else if ( pedido.getQualificacaoConferencia().equals("B2"))
                  pontos = 12;
            else if ( pedido.getQualificacaoConferencia().equals("B3"))
                  pontos = 10;
            else if ( pedido.getQualificacaoConferencia().equals("B4"))
                  pontos = 8;
            else if ( pedido.getQualificacaoConferencia().equals("B5"))
                  pontos = 6;
            else
                  pontos = 4;

            if ( pedido.getFinanciamento().equals("Mérito c/ Financiamento"))
                  pontos += 10;
            if ( pedido.getFinanciamento().equals("Mérito s/ Financiamento"))
                  pontos += 6;

            if ( pedido.getNomeConferencia().equals("SBSI") || pedido.getNomeConferencia().equals("ICEIS")
                                                            || pedido.getNomeConferencia().equals("AMCIS")
                                                            || pedido.getNomeConferencia().equals("CAiSE")
                                                            || pedido.getNomeConferencia().equals("ICIS")
                                                            || pedido.getNomeConferencia().equals("ECIS") )
                  pontos += 6;

            if ( pedido.getNumeroArtigos() > 1 )
                  pontos += 2;

            return pontos;
      }

      public String CalculaConceito(int pontos)
      {
            String conceito = null;

            if ( pontos >= 2 && pontos < 4 )
                  conceito = "E";
            else if ( pontos >= 4 && pontos < 8 )
                  conceito = "D";
            else if ( pontos >= 8 && pontos < 12 )
                  conceito = "C";
            else if ( pontos >= 12 && pontos < 16 )
                  conceito = "B";
            else if ( pontos >= 12 )
                  conceito = "A";

            return conceito;
      }

      public int CalculaFinanciamentoSugerido(String conceito, String cota)
      {
            int valorSugerido = 0;
            String moeda = null;

            if ( conceito.equals("A") )
            {
                  if ( cota.equals("Cota Internacional") )
                        valorSugerido = 3000;

                  else
                        valorSugerido = 4000;
            }

            else if ( conceito.equals("B") )
            {
                  if ( cota.equals("Cota Internacional") )
                        valorSugerido = 2500;
                  else
                        valorSugerido = 3000;
            }

            else if ( conceito.equals("C") )
                   valorSugerido = 3000;

            else if ( conceito.equals("D") )
            {
                  if ( cota.equals("Cota Internacional") )
                        valorSugerido = 1500;
                  else
                        valorSugerido = 1000;
            }

            else if ( conceito.equals("E") )
            {
                  if ( cota.equals("Cota Internacional") )
                        valorSugerido = 1000;
                  else
                        valorSugerido = 500;
            }

            return valorSugerido;
      }
}
