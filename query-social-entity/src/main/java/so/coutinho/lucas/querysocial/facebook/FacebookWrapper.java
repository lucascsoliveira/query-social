package so.coutinho.lucas.querysocial.facebook;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import so.coutinho.lucas.querysocial.util.Series;

/**
 *
 * @author lucas.oliveira
 */
public abstract class FacebookWrapper {

    public abstract List<Page> searchPages(String query);

    public abstract void login(String accessToken);

    public abstract void logout();

    public abstract boolean isLogged();

    public abstract Series getLikes(String pageId, Calendar start, Calendar end);

    public abstract Series getStoryTellers(String pageId, Calendar start, Calendar end);

    public List<Series> getLikes(List<String> pageIds, Calendar start, Calendar end) {
        List<Series> series = new ArrayList<>();

        for (String id : pageIds) {
            series.add(getLikes(id, start, end));
        }

        return series;
    }

    public List<Series> getStoryTellers(List<String> pageIds, Calendar start, Calendar end) {
        List<Series> series = new ArrayList<>();

        for (String id : pageIds) {
            series.add(getStoryTellers(id, start, end));
        }

        return series;
    }

    public User getUser() {
        return getUser("me");
    }

    public abstract User getUser(String id);

    public abstract Page getPage(String id);

}
