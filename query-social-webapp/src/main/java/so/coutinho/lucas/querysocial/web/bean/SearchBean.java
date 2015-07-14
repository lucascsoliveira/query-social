package so.coutinho.lucas.querysocial.web.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import lombok.Getter;
import lombok.Setter;
import so.coutinho.lucas.querysocial.facebook.Page;

/**
 *
 * @author Lucas
 */
@Getter
@Setter
@ManagedBean
public class SearchBean {

    private Date startDate;
    private Date endDate;

    private List<Page> pages = new ArrayList<>();
    private List<Page> selectedPages = new ArrayList<>();

}
