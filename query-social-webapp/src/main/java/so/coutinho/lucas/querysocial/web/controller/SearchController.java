package so.coutinho.lucas.querysocial.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import so.coutinho.lucas.querysocial.web.bean.ContextUrls;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import so.coutinho.lucas.querysocial.facebook.FacebookWrapper;
import so.coutinho.lucas.querysocial.facebook.Page;
import so.coutinho.lucas.querysocial.web.bean.SearchBean;
import so.coutinho.lucas.querysocial.web.bean.SessionAttributes;

/**
 *
 * @author Lucas
 */
@Controller
@RequestMapping(ContextUrls.SEARCH)
@Scope(WebApplicationContext.SCOPE_SESSION)
public class SearchController extends AbstractController {

    @Autowired
    SearchBean searchBean;

    @RequestMapping(method = RequestMethod.GET)
    public String loadForm(HttpSession session, ModelMap model) {
        clearBean();
        return doFilter(session, "search");
    }

    @RequestMapping(method = RequestMethod.POST)
    public String loadList(HttpSession session, ModelMap model, @RequestParam("query") String query) {
        FacebookWrapper fbWrapper = (FacebookWrapper) session.getAttribute(SessionAttributes.FB_SESSION);

        List<Page> lista = fbWrapper.searchPages(query);
        searchBean.setPages(lista);

        model.addAttribute("showSearch", true);

        return doFilter(session, "search");
    }

    @RequestMapping(value = ContextUrls.RESULT, method = RequestMethod.POST)
    public String getResult(HttpSession session, ModelMap model, @RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate, @RequestParam("selectedPages") String selectedPages) {
        System.out.println(startDate);
        System.out.println(endDate);
        System.out.println(selectedPages);

        return doFilter(session, "search-result");
    }

    private void clearBean() {
        searchBean.getPages().clear();
        searchBean.getSelectedPages().clear();
    }

}
