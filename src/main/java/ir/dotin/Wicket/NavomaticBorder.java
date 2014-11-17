package ir.dotin.Wicket;

/**
 * Created by niloofar on 11/8/14.
 */
import org.apache.wicket.request.component.IRequestablePage;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.markup.html.border.BoxBorder;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class NavomaticBorder extends Border implements IRequestablePage {
    public NavomaticBorder(final String componentName) {
        super(componentName);

        addToBorder(new BoxBorder("navigationBorder"));
        addToBorder(new BoxBorder("bodyBorder"));
    }


    @Override
    public void renderPage() {

    }

    @Override
    public boolean isBookmarkable() {
        return false;
    }

    @Override
    public int getRenderCount() {
        return 0;
    }

    @Override
    public boolean wasCreatedBookmarkable() {
        return false;
    }

    @Override
    public PageParameters getPageParameters() {
        return null;
    }

    @Override
    public boolean isPageStateless() {
        return false;
    }

    @Override
    public int getPageId() {
        return 0;
    }

    @Override
    public boolean setFreezePageId(boolean freeze) {
        return false;
    }
}