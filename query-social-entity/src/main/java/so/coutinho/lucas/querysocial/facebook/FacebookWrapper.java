package so.coutinho.lucas.querysocial.facebook;

import java.util.List;

/**
 *
 * @author lucas.oliveira
 */
public interface FacebookWrapper {

    public List<Page> searchPages(String query);

    public void login(String accessToken);

    public void logout();

    public boolean isLogged();

    public List<Object> getLikes(Page page, Long epochTimeStart, Long epochTimeEnd);

    public List<Object> getStoryTellers(Page page, Long epochTimeStart, Long epochTimeEnd);

    public User getUser();

    public User getUser(Long id);

    public User getUser(String id);

    public Page getPage(Long id);

    public Page getPage(String id);

}
