/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.coutinho.lucas.querysocial.web.controller;

import javax.servlet.http.HttpSession;

/**
 *
 * @author Lucas
 */
public class AbstractController {

    protected final String USER_SESSION = "userSession";

    public String doFilter(HttpSession session, String page) {
        if (session.getAttribute(USER_SESSION) != null) {
            return page;
        }

        return "redirect:/";
    }

}
