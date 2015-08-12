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
    public static final String MATCH = "/match";
    public static final String SEARCH = "/search";

    /**
     * Metodos
     */
    public static final String RESULT = "/result";
    public static final String RESULT_LIKES = RESULT + "/likes";
    public static final String RESULT_STORYTELLERS = RESULT + "/storytellers";
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
    public String match = MATCH;
    public String search = SEARCH;

    /**
     * Metodos
     */
    public String result = RESULT;
    public String resultLikes = RESULT_LIKES;
    public String resultStorytellers = RESULT_STORYTELLERS;

}
