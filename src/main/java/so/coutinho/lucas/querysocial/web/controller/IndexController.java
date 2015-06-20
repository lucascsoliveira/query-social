package so.coutinho.lucas.querysocial.web.controller;

import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Lucas
 */
@Controller
@RequestMapping("/")
@SessionScoped
public class IndexController {

    @RequestMapping(method = RequestMethod.GET)
    public String loadForm(HttpSession session, ModelMap model) {
        if (session.getAttribute("usuarioSessao") != null) {
            return "index";
        }

        return "signin";
    }

    @RequestMapping(value = ContextUrls.LOGIN, method = RequestMethod.POST)
    public String login(HttpSession session, ModelMap model, @RequestParam("accessToken") String accessToken) {
        if (accessToken.equalsIgnoreCase("brunella")) {
            session.setAttribute("usuarioSessao", "LOGGED");

            return "redirect:";
        }

        model.addAttribute("loginErro", true);

        return "signin";
    }

    @RequestMapping(value = ContextUrls.LOGOUT, method = RequestMethod.GET)
    public String logout(HttpSession session, ModelMap model) {
        session.invalidate();

        return "redirect:";
    }

}
