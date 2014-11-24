package ir.dotin.qc.ticketReserve.view;

/**
 * Created by niloofar on 11/8/14.
 */

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.panel.Panel;

public class AdminPanel extends Panel {

    public AdminPanel(final String componentName) {
        super(componentName);

        add(new MarkupContainer("navigationBorder") {
        });
        add(new MarkupContainer("bodyBorder") {
        });
    }

}