package so.coutinho.lucas.querysocial.facebook;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Lucas
 */
@Getter
@Setter
public class UserImpl extends FacebookEntityImpl implements User {

    private String email;

    public UserImpl() {
        //Do nothing
    }

    public UserImpl(com.restfb.types.User user) {
        super(user.getId(), user.getName(), user.getPicture().getUrl());

        email = user.getEmail();
    }

}
