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
    private byte[] profilePicture;
    private String profilePictureURL;

    public FacebookEntityImpl() {
        //Do nothing
    }

    public FacebookEntityImpl(String id, String name, String profilePictureURL) {
        this.id = id;
        this.name = name;
        this.profilePicture = convertPicture(profilePictureURL);
        this.profilePictureURL = profilePictureURL;
    }

    private byte[] convertPicture(String url) {
        //TODO: Converter perfil (imagem) para byte array
        return null;
    }

}
