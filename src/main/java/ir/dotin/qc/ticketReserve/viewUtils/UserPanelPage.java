package ir.dotin.qc.ticketReserve.viewUtils;

/**
 * Created by niloofar on 11/8/14.
 */

import ir.dotin.qc.ticketReserve.flight.SearchFlightPage;
import ir.dotin.qc.ticketReserve.flight.ViewReservesPage;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

public class UserPanelPage extends Panel {

    public UserPanelPage(final String componentName) {
        super(componentName);

        MarkupContainer list = new MarkupContainer("list") {
        };
        list.add(new Link<String>("search-flight") {

            @Override
            public void onClick() {
                setResponsePage(SearchFlightPage.class);
            }
        });
        list.add(new Link<String>("view-reserve") {

            @Override
            public void onClick() {
                setResponsePage(ViewReservesPage.class);
            }
        });
        list.add(new Link<String>("menu-user") {

            @Override
            public void onClick() {
                setResponsePage(MenuUserPage.class);
            }
        });
        add(list);
        add(new MarkupContainer("body") {
        });
    }

}