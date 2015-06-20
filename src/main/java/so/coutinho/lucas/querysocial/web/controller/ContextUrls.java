package so.coutinho.lucas.querysocial.web.controller;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
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
    public static final String INDEX = "/index";
    public static final String SEARCH = "/search";

    /**
     * Metodos
     */
    public static final String RESULT = "/result";

    /**
     * Metodos auxiliares
     */
//    public String getBaseURL() {
//        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
//        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
//        String url = request.getRequestURL().toString();
//        String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath();
//
//        return baseURL;
//    }
//    
    public String getRoot() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();

        return request.getContextPath();
    }

    /* ---------------------------------------------------------------------- */
    /**
     * Contexts
     */
    private final String index = getRoot() + INDEX;
    private final String search = getRoot() + SEARCH;

    /**
     * Metodos
     */
    private final String result = RESULT;

}
