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
@RequestMapping(ContextUrls.SEARCH)
@SessionScoped
public class SearchController extends AbstractController {

    @RequestMapping(method = RequestMethod.GET)
    public String loadForm(HttpSession session, ModelMap model) {
        return doFilter(session, "search");
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String loadList(HttpSession session, ModelMap model) {
        model.addAttribute("exibeResultado", true);
        return doFilter(session, "search");
    }

    @RequestMapping(value = ContextUrls.RESULT, method = RequestMethod.POST)
    public String getResult(HttpSession session, ModelMap model) {
        return doFilter(session, "search-result");
    }

}
