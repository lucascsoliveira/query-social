package so.coutinho.lucas.querysocial.web.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import so.coutinho.lucas.querysocial.facebook.Page;

/**
 *
 * @author Lucas
 */
@Getter
@Setter
@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class SearchBean {

    private Date startDate;
    private Date endDate;
    private List<Page> pages = new ArrayList<>();
    private List<Page> selectedPages = new ArrayList<>();

}
