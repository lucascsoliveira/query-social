package so.coutinho.lucas.querysocial.web.controller;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author Lucas
 */
@ApplicationScoped
@ManagedBean
public class ContextUrls {

    /**
     * Contexts
     */
    public static final String LOGIN = "/login";
    public static final String LOGOUT = "/logout";
    public static final String SEARCH = "/search";

    /**
     * Metodos
     */
    public static final String RESULT = "/result";

    public String getLogin() {
        return LOGIN;
    }

    public String getLogout() {
        return LOGOUT;
    }

    public String getSearch() {
        return SEARCH;
    }

    public String getResult() {
        return RESULT;
    }

}
