package so.coutinho.lucas.querysocial.web.controller;

import so.coutinho.lucas.querysocial.web.bean.ContextUrls;
import static so.coutinho.lucas.querysocial.web.bean.SessionAttributes.*;
import javax.servlet.http.HttpSession;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import so.coutinho.lucas.querysocial.facebook.DefaultFacebookWrapper;
import so.coutinho.lucas.querysocial.facebook.FacebookWrapper;
import so.coutinho.lucas.querysocial.facebook.User;

/**
 *
 * @author Lucas
 */
@Controller
@RequestMapping("/")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class IndexController extends AbstractController {

    @RequestMapping(method = RequestMethod.GET)
    public String loadForm(HttpSession session, ModelMap model) {

        if (session.getAttribute(FB_SESSION) != null) {
            return "index";
        }

        return "signin";
    }

    @RequestMapping(value = ContextUrls.LOGIN, method = RequestMethod.POST)
    public String login(HttpSession session, ModelMap model, @RequestParam("accessToken") String accessToken) {
        FacebookWrapper facebookWrapper = new DefaultFacebookWrapper();
        facebookWrapper.login(accessToken);
        User user = facebookWrapper.getUser();

        session.setAttribute(USER_LINK, "#");
        session.setAttribute(USER_NAME, user.getName());
        session.setAttribute(USER_PICTURE, user.getProfilePictureURL());
        session.setAttribute(FB_SESSION, facebookWrapper);

        return "redirect:/";
    }

    @RequestMapping(value = ContextUrls.LOGOUT, method = RequestMethod.GET)
    public String logout(HttpSession session, ModelMap model) {
        session.invalidate();

        return "redirect:/";
    }

}
