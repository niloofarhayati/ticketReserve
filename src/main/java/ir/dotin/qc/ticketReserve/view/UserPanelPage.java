package ir.dotin.qc.ticketReserve.view;

/**
 * Created by niloofar on 11/8/14.
 */

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.border.Border;

public class UserPanelPage extends Border {

    public UserPanelPage(final String componentName) {
        super(componentName);

        addToBorder(new MarkupContainer("list") {
        });
        addToBorder(new MarkupContainer("body") {
        });
    }

}