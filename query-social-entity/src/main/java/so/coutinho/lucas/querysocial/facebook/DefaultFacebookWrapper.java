package so.coutinho.lucas.querysocial.facebook;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.exception.FacebookOAuthException;
import com.restfb.json.JsonObject;
import com.restfb.types.Insight;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.TimeZone;
import so.coutinho.lucas.querysocial.util.Series;

/**
 *
 * @author lucas.oliveira
 */
public class DefaultFacebookWrapper extends FacebookWrapper {

    private static final String PAGE_FIELDS = "id, about, category, likes, link, name, picture, talking_about_count, username";
    private static final String USER_FIELDS = "first_name, gender, email, last_name, locale, name, picture";

    private static final String INSIGHTS = "/insights";
    private static final String INSIGHTS_PAGE_FANS = INSIGHTS + "/page_fans_country";
    private static final String INSIGHTS_PAGE_STORYTELLERS = INSIGHTS + "/page_storytellers_by_country";

    private static final String PERIOD_DAY = "/day";
    private static final String PERIOD_WEEK = "/week";
    private static final String PERIOD_DAYS_28 = "/days_28";
    private static final String PERIOD_LIFETIME = "/lifetime";

    private static final int DATE_RANGE_DAYS = 92;
    private static final Long DATE_RANGE_IN_MILLIS = new Long("7948800000");

    private FacebookClient facebookClient;

    @Override
    public void login(String accessToken) {
        facebookClient = new DefaultFacebookClient(accessToken, Version.VERSION_2_3);
    }

    @Override
    public void logout() {
        facebookClient = null;
    }

    @Override
    public boolean isLogged() {
        if (facebookClient != null) {
            try {
                this.getUser();
                return true;
            } catch (FacebookOAuthException ex) {
                //Do nothing
            }
        }

        return false;
    }

    @Override
    public User getUser(String id) {
        return new UserImpl(facebookClient.fetchObject(id, com.restfb.types.User.class, Parameter.with("fields", USER_FIELDS)));
    }

    @Override
    public Page getPage(String id) {
        return new PageImpl(facebookClient.fetchObject(id, com.restfb.types.Page.class, Parameter.with("fields", PAGE_FIELDS)));
    }

    @Override
    public Series getLikes(String pageId, Calendar start, Calendar end) {
        return getSeries(pageId, start, end, INSIGHTS_PAGE_FANS, PERIOD_LIFETIME);
    }

    @Override
    public Series getStoryTellers(String pageId, Calendar start, Calendar end) {
        return getSeries(pageId, start, end, INSIGHTS_PAGE_STORYTELLERS, PERIOD_DAY);
    }

    @Override
    public List<Page> searchPages(String query) {
        List<Page> pages = new ArrayList<>();
        Connection<com.restfb.types.Page> targetedSearch = facebookClient.fetchConnection("search", com.restfb.types.Page.class, Parameter.with("q", query), Parameter.with("type", "page"), Parameter.with("fields", PAGE_FIELDS));

        boolean runNext = true;

        while (runNext) {
            runNext = false;

            for (com.restfb.types.Page page : targetedSearch.getData()) {
                pages.add(new PageImpl(page));
            }

            if (targetedSearch.hasNext()) {
                runNext = true;
                targetedSearch = facebookClient.fetchConnectionPage(targetedSearch.getNextPageUrl(), com.restfb.types.Page.class);
            }
        }
        return pages;
    }

    private Long toEpochTime(Calendar calendar) {
        return calendar.getTimeInMillis() / 1000;
    }

    private Long sumValues(JsonObject valuesByCountry) {
        Iterator countries = valuesByCountry.keys();
        long total = 0;

        while (countries.hasNext()) {
            String country = countries.next().toString();
            total += valuesByCountry.getLong(country);
        }

        return total;
    }

    private long formatDate(String unformatted) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
        String REGEX = "^(\\d{4})-(\\d{2})-(\\d{2})T(\\d{2}):(\\d{2}):(\\d{2})(\\+\\d{4})$";

        if (unformatted.matches(REGEX)) {
            Integer year = new Integer(unformatted.replaceAll(REGEX, "$1"));
            Integer month = new Integer(unformatted.replaceAll(REGEX, "$2"));
            Integer day = new Integer(unformatted.replaceAll(REGEX, "$3"));

            return new GregorianCalendar(year, month - 1, day).getTimeInMillis();
        } else {
            return 0;
        }
    }

    //since (include) - until(exclude)//
    private Series getSeries(String idPage, Calendar since, Calendar until, String insightPage, String insightPeriod) {
        String name;
        Object[][] data;

        name = getPage(idPage).getName();
        data = getInsightData(idPage, since, until, insightPage, insightPeriod);

        return new Series(name, data);
    }

    private List<List<Object>> getPageInsights(String idPage, Calendar since, Calendar until, String insightPage, String insightPeriod) {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        List<List<Object>> data = new ArrayList<>();

        long millisSince = since.getTimeInMillis();
        long millisUntil = until.getTimeInMillis();

        // SE UNTIL - SINCE <= 3 MESES
        if (millisUntil - millisSince <= DATE_RANGE_IN_MILLIS) {

            // Para cada dia, somar os valores de todos os países;
            // E, add para List< Par<Date,Integer> >
            long epochSince = toEpochTime(since), epochUntil = toEpochTime(until);

            Connection<Insight> insights = facebookClient.fetchConnection(
                    idPage + insightPage + insightPeriod,
                    Insight.class,
                    Parameter.with("since", epochSince),
                    Parameter.with("until", epochUntil));

            for (Insight insight : insights.getData()) {
                for (JsonObject jo : insight.getValues()) {
                    List<Object> list2 = new ArrayList<>();

                    Long timeMillisUTC = formatDate(jo.getString("end_time"));
                    Long sumValues = jo.getString("value").length() <= 2 ? 0 : sumValues(jo.getJsonObject("value"));

                    list2.add(timeMillisUTC);
                    list2.add(sumValues);

                    data.add(list2);
                }
            }
        } else {
            // return pageLikes(id, since, since+3meses).addAll(pageLikes(id, since+3meses, until);
            Calendar sinceClone = (Calendar) since.clone();
            sinceClone.add(Calendar.DAY_OF_MONTH, DATE_RANGE_DAYS - 1);
            data.addAll(getPageInsights(idPage, since, sinceClone, insightPage, insightPeriod));
            data.addAll(getPageInsights(idPage, sinceClone, until, insightPage, insightPeriod));
        }

        return data;
    }

    private Object[][] getInsightData(String idPage, Calendar since, Calendar until, String insightPage, String insightPeriod) {
        List<List<Object>> listData = getPageInsights(idPage, since, until, insightPage, insightPeriod);
        Object[][] data = new Object[listData.size()][2];

        int index = 0;
        for (List<Object> line : listData) {
            data[index][0] = line.get(0);
            data[index][1] = line.get(1);

            index++;
        }

        return data;
    }

}
