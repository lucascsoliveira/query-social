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
@ManagedBean
@ApplicationScoped
@Getter
public class ContextUrls {

    /**
     * Contexts
     */
    public static final String INDEX = "/index";
    public static final String SEARCH = "/search";
    public static final String SEARCH_RESULT = "/result";

    public String getBaseURL() {
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        HttpServletRequest request = (HttpServletRequest) externalContext.getRequest();
        String url = request.getRequestURL().toString();
        String baseURL = url.substring(0, url.length() - request.getRequestURI().length()) + request.getContextPath();

        return baseURL;
    }

}
