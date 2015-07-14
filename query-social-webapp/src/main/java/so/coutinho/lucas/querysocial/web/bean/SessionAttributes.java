package so.coutinho.lucas.querysocial.web.bean;

import lombok.Getter;
import org.springframework.stereotype.Controller;

/**
 *
 * @author Lucas
 */
@Getter
@Controller
public class SessionAttributes {

    public static final String FB_SESSION = "fbSession";
    public static final String USER_LINK = "userLink";
    public static final String USER_NAME = "userName";
    public static final String USER_PICTURE = "userPicture";

    private final String fbSession = FB_SESSION;
    private final String userLink = USER_LINK;
    private final String userName = USER_NAME;
    private final String userPicture = USER_PICTURE;

}
