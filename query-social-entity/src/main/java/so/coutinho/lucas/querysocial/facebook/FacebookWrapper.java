package so.coutinho.lucas.querysocial.facebook;

import java.util.Calendar;
import java.util.List;
import so.coutinho.lucas.querysocial.util.Pair;

/**
 *
 * @author lucas.oliveira
 */
public interface FacebookWrapper {

    public List<Page> searchPages(String query);

    public void login(String accessToken);

    public void logout();

    public boolean isLogged();

    public List<Pair<String, Long>> getLikes(String pageId, Calendar start, Calendar end);

    public List<Pair<String, Long>> getStoryTellers(String pageId, Calendar start, Calendar end);

    public User getUser();

    public User getUser(Long id);

    public User getUser(String id);

    public Page getPage(Long id);

    public Page getPage(String id);

}
