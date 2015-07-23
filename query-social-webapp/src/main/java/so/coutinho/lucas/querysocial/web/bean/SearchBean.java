package so.coutinho.lucas.querysocial.web.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import so.coutinho.lucas.querysocial.facebook.FacebookWrapper;
import so.coutinho.lucas.querysocial.facebook.Page;

/**
 *
 * @author Lucas
 */
@Getter
@Setter
@Component
public class SearchBean {

    private Calendar startDate;
    private Calendar endDate;
    private FacebookWrapper facebookWrapper;
    private List<Page> pages = new ArrayList<>();
    private List<Page> selectedPages = new ArrayList<>();

}
