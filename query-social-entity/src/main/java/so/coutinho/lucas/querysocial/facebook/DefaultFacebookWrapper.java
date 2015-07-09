package so.coutinho.lucas.querysocial.facebook;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.exception.FacebookOAuthException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lucas.oliveira
 */
public class DefaultFacebookWrapper implements FacebookWrapper {

    private static final String PAGE_FIELDS = "id, about, category, likes, link, name, picture, talking_about_count, username";
    private static final String USER_FIELDS = "first_name, gender, email, last_name, locale, name, picture";

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
    public User getUser() {
        return getUser("me");
    }

    @Override
    public User getUser(Long id) {
        return getUser(id.toString());
    }

    @Override
    public User getUser(String id) {
        return new UserImpl(facebookClient.fetchObject(id, com.restfb.types.User.class, Parameter.with("fields", USER_FIELDS)));
    }

    @Override
    public Page getPage(Long id) {
        return getPage(id.toString());
    }

    @Override
    public Page getPage(String id) {
        return new PageImpl(facebookClient.fetchObject(id, com.restfb.types.Page.class, Parameter.with("fields", PAGE_FIELDS)));
    }

    @Override
    public List<Object> getLikes(Page page, Long epochTimeStart, Long epochTimeEnd) {
        //TODO: Implementar método
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Object> getStoryTellers(Page page, Long epochTimeStart, Long epochTimeEnd) {
        //TODO: Implementar método
        throw new UnsupportedOperationException("Not supported yet.");
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

    //TODO: Add first_name, gender, email, last_name, locale, name, picture to User(?);
}
