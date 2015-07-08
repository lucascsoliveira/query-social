package so.coutinho.lucas.querysocial.facebook;

/**
 *
 * @author lucas.oliveira
 */
public interface Page extends FacebookEntity {

    public String getAbout();

    public String getCategory();

    public Long getLikes();

    public String getLink();

    public Long getTalkingAboutCount();

    public String getUsername();

}
