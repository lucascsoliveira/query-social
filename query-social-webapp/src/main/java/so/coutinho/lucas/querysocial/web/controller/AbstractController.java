/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.coutinho.lucas.querysocial.web.controller;

import javax.servlet.http.HttpSession;
import static so.coutinho.lucas.querysocial.web.bean.SessionAttributes.*;

/**
 *
 * @author Lucas
 */
public class AbstractController {

    public String doFilter(HttpSession session, String page) {
        if (session.getAttribute(FB_SESSION) != null) {
            return page;
        }

        return "redirect:/";
    }

}
