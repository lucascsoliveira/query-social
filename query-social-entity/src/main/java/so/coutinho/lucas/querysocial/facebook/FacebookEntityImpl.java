package so.coutinho.lucas.querysocial.facebook;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Lucas
 */
@Getter
@Setter
abstract class FacebookEntityImpl implements FacebookEntity {

    private String id;
    private String name;
    private String profilePicture;
    private String profilePictureURL;

    public FacebookEntityImpl() {
        //Do nothing
    }

    public FacebookEntityImpl(String id, String name, String profilePicture) {
        this.id = id;
        this.name = name;
        this.profilePicture = convertPicture(profilePicture);
        this.profilePictureURL = profilePicture;
    }

    private String convertPicture(String url) {
        return url.replaceAll("&", "&amp;");
    }

}
