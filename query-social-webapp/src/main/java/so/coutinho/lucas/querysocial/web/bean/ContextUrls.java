package so.coutinho.lucas.querysocial.web.bean;

import lombok.Getter;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Lucas
 */
@Getter
@Controller
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
//                                                                            //
//============================================================================//
//
    /**
     * Contexts
     */
    public String login = LOGIN;
    public String logout = LOGOUT;
    public String search = SEARCH;

    /**
     * Metodos
     */
    public String result = RESULT;

}
