package so.coutinho.lucas.querysocial.facebook;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Lucas
 */
@Getter
@Setter
class PageImpl extends FacebookEntityImpl implements Page {

    private String about;
    private String category;
    private Long likes;
    private String link;
    private Long talkingAboutCount;
    private String username;

    public PageImpl() {
        //Do nothing
    }

    public PageImpl(com.restfb.types.Page page) {
        super(page.getId(), page.getName(), page.getPicture());

        this.about = page.getAbout();
        this.category = page.getCategory();
        this.likes = page.getLikes();
        this.link = page.getLink();
        this.talkingAboutCount = page.getTalkingAboutCount();
        this.username = page.getUsername();
    }

}
