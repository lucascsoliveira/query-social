package so.coutinho.lucas.querysocial.web.controller;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import so.coutinho.lucas.querysocial.web.bean.ContextUrls;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
    private SearchBean searchBean;

    @RequestMapping(method = RequestMethod.GET)
    public String loadForm(HttpSession session, ModelMap model) {
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
        searchBean.setStartDate(stringToCalendar(startDate));
        searchBean.setEndDate(stringToCalendar(endDate));
        searchBean.setFacebookWrapper((FacebookWrapper) session.getAttribute(SessionAttributes.FB_SESSION));
        searchBean.setSelectedPagesIds(stringToList(selectedPages));

        return doFilter(session, "search-result");
    }

    @ResponseBody
    @RequestMapping(value = ContextUrls.RESULT + "/json/likes", method = RequestMethod.GET)
    public ResponseEntity<String> getJsonLikes() {
        List<String> ids = searchBean.getSelectedPagesIds();
        Calendar start = searchBean.getStartDate(), end = searchBean.getEndDate();
        FacebookWrapper facebookWrapper = searchBean.getFacebookWrapper();

        return new ResponseEntity<>(new Gson().toJson(facebookWrapper.getLikes(ids, start, end)), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = ContextUrls.RESULT + "/json/storytellers", method = RequestMethod.GET)
    public ResponseEntity<String> getJsonStoryTellers() {
        List<String> ids = searchBean.getSelectedPagesIds();
        Calendar start = searchBean.getStartDate(), end = searchBean.getEndDate();
        FacebookWrapper facebookWrapper = searchBean.getFacebookWrapper();

        return new ResponseEntity<>(new Gson().toJson(facebookWrapper.getStoryTellers(ids, start, end)), HttpStatus.OK);
    }

    private Calendar stringToCalendar(String date) {
        String[] dateFields = date.split("/");

        return new GregorianCalendar(Integer.parseInt(dateFields[2]), Integer.parseInt(dateFields[1]) - 1, Integer.parseInt(dateFields[0]));
    }

    private List<String> stringToList(String ids) {
        List<String> listIds;

        if (ids.equals("[]")) {
            listIds = new ArrayList<>();
        } else {
            ids = ids.replace("[\"", "");
            ids = ids.replace("\"]", "");

            listIds = Arrays.asList(ids.split("\",\""));
        }
        return listIds;
    }

}
