package ir.dotin.qc.ticketReserve.viewUtils;

/**
 * Created by niloofar on 11/8/14.
 */

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;

public class AdminPanelPage extends Panel {

    public AdminPanelPage(final String componentName) {
        super(componentName);

        add(new MarkupContainer("list") {
        });
        add(new MarkupContainer("body") {
        });
    }

}