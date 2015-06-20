package so.coutinho.lucas.querysocial.web.controller;

import javax.faces.bean.SessionScoped;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Lucas
 */
@Controller
@RequestMapping(ContextUrls.SEARCH)
@SessionScoped
public class SearchController {

    @RequestMapping(method = RequestMethod.GET)
    public String loadForm(ModelMap model) {

        return "search";
    }

    @RequestMapping(value = ContextUrls.SEARCH_RESULT, method = RequestMethod.GET)
    public String getResult(ModelMap model) {

        return "search-result";
    }

}
