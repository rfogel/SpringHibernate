/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Fogel
 */
@Controller
public class LogoutController {

      @RequestMapping("logout.html")
      public String logout(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
            HttpSession session = hsr.getSession();
            session.invalidate();
            return "redirect:login.html";
      }
}
