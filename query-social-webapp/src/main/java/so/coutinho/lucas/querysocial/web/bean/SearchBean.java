package so.coutinho.lucas.querysocial.web.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.LineChartModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.WebApplicationContext;
import so.coutinho.lucas.querysocial.facebook.FacebookWrapper;
import so.coutinho.lucas.querysocial.facebook.Page;
import so.coutinho.lucas.querysocial.util.Pair;

/**
 *
 * @author Lucas
 */
@Getter
@Setter
@Controller
@Scope(WebApplicationContext.SCOPE_SESSION)
public class SearchBean {

    private Calendar startDate;
    private Calendar endDate;
    private FacebookWrapper facebookWrapper;
    private List<Page> pages = new ArrayList<>();
    private List<Page> selectedPages = new ArrayList<>();

}
