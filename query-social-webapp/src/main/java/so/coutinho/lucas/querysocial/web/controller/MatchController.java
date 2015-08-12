package so.coutinho.lucas.querysocial.web.controller;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
import so.coutinho.lucas.querysocial.web.bean.ContextUrls;
import so.coutinho.lucas.querysocial.web.bean.SearchBean;
import so.coutinho.lucas.querysocial.web.bean.SessionAttributes;

/**
 *
 * @author Lucas
 */
@Controller
@RequestMapping(ContextUrls.MATCH)
@Scope(WebApplicationContext.SCOPE_SESSION)
public class MatchController extends AbstractController {

    @Autowired
    private SearchBean searchBean;

    @RequestMapping(method = RequestMethod.GET)
    public String loadForm(HttpSession session, ModelMap model) {
        return doFilter(session, "search-advanced");
    }

    @RequestMapping(value = ContextUrls.RESULT, method = RequestMethod.POST)
    public String getResult(HttpSession session, ModelMap model,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            @RequestParam("pageId") String pageId,
            @RequestParam("pageId2") String pageId2) {

        System.out.println(startDate);
        System.out.println(endDate);

        searchBean.setStartDate(stringToCalendar(startDate));
        searchBean.setEndDate(stringToCalendar(endDate));
        searchBean.setFacebookWrapper((FacebookWrapper) session.getAttribute(SessionAttributes.FB_SESSION));
        List<String> selectedPages = new ArrayList<>();
        selectedPages.add(pageId);
        selectedPages.add(pageId2);
        searchBean.setSelectedPagesIds(selectedPages);

        return "search-result";
    }

    @ResponseBody
    @RequestMapping(value = ContextUrls.RESULT_LIKES, method = RequestMethod.GET)
    public ResponseEntity<String> getJsonLikes() {
        List<String> ids = searchBean.getSelectedPagesIds();
        Calendar start = searchBean.getStartDate(), end = searchBean.getEndDate();
        FacebookWrapper facebookWrapper = searchBean.getFacebookWrapper();

        return new ResponseEntity<>(new Gson().toJson(facebookWrapper.getLikes(ids, start, end)), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = ContextUrls.RESULT_STORYTELLERS, method = RequestMethod.GET)
    public ResponseEntity<String> getJsonStoryTellers() {
        List<String> ids = searchBean.getSelectedPagesIds();
        Calendar start = searchBean.getStartDate(), end = searchBean.getEndDate();
        FacebookWrapper facebookWrapper = searchBean.getFacebookWrapper();

        return new ResponseEntity<>(new Gson().toJson(facebookWrapper.getStoryTellers(ids, start, end)), HttpStatus.OK);
    }

}
