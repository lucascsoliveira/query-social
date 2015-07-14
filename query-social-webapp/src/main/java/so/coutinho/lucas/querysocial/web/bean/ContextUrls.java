package so.coutinho.lucas.querysocial.web.bean;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import lombok.Getter;

/**
 *
 * @author Lucas
 */
@ApplicationScoped
@ManagedBean
@Getter
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
//
//============================================================================//
//
    /**
     * Contexts
     */
    public final String login = LOGIN;
    public final String logout = LOGOUT;
    public final String search = SEARCH;

    /**
     * Metodos
     */
    public final String result = RESULT;

}
