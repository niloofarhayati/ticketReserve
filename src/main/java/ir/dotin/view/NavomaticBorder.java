package ir.dotin.view;

/**
 * Created by niloofar on 11/8/14.
 */

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.border.Border;
import org.apache.wicket.markup.html.border.BorderPanel;
import org.apache.wicket.markup.html.border.BoxBorder;
import org.apache.wicket.markup.html.form.ListChoice;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigation;
import org.apache.wicket.markup.repeater.data.GridView;

public class NavomaticBorder extends Border {

    public NavomaticBorder(final String componentName) {
        super(componentName);

        addToBorder(new MarkupContainer("navigationBorder") {
        });
        addToBorder(new MarkupContainer("bodyBorder") {
        });
    }

}