package so.coutinho.lucas.querysocial.web.controller;

import so.coutinho.lucas.querysocial.web.bean.ContextUrls;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import so.coutinho.lucas.querysocial.facebook.FacebookWrapper;
import so.coutinho.lucas.querysocial.web.bean.SessionAttributes;

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
    public String loadList(HttpSession session, ModelMap model, @RequestParam("query") String query) {
        FacebookWrapper fbWrapper = (FacebookWrapper) session.getAttribute(SessionAttributes.FB_SESSION);

        //FIXME: String QUERY não pode ser vazia.
        //TODO: Preencher LIST<PAGE> PAGES em SearchBean.
        return doFilter(session, "search");
    }

    @RequestMapping(value = ContextUrls.RESULT, method = RequestMethod.POST)
    public String getResult(HttpSession session, ModelMap model) {
        return doFilter(session, "search-result");
    }

}
