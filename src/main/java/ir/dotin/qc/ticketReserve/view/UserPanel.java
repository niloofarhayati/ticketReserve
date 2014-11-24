package ir.dotin.qc.ticketReserve.view;

/**
 * Created by niloofar on 11/8/14.
 */

import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.border.Border;

public class UserPanel extends Border {

    public UserPanel(final String componentName) {
        super(componentName);

        addToBorder(new MarkupContainer("navigationBorder") {
        });
        addToBorder(new MarkupContainer("bodyBorder") {
        });
    }

}