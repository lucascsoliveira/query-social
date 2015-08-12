package so.coutinho.lucas.querysocial.web.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import javax.servlet.http.HttpSession;
import static so.coutinho.lucas.querysocial.web.bean.SessionAttributes.*;

/**
 *
 * @author Lucas
 */
public class AbstractController implements Serializable {

    public String doFilter(HttpSession session, String page) {
        if (session.getAttribute(FB_SESSION) != null) {
            return page;
        }

        return "redirect:/";
    }

    public Calendar stringToCalendar(String date) {
        String[] dateFields = date.split("/");

        return new GregorianCalendar(Integer.parseInt(dateFields[2]), Integer.parseInt(dateFields[1]) - 1, Integer.parseInt(dateFields[0]));
    }

    public List<String> stringToList(String ids) {
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
