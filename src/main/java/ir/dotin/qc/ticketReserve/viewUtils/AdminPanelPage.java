package ir.dotin.qc.ticketReserve.viewUtils;

/**
 * Created by niloofar on 11/8/14.
 */

import ir.dotin.qc.ticketReserve.flight.AddFlightPage;
import ir.dotin.qc.ticketReserve.flight.ViewFlightsPage;
import ir.dotin.qc.ticketReserve.user.AddUserPage;
import ir.dotin.qc.ticketReserve.user.DeleteUserPage;
import ir.dotin.qc.ticketReserve.user.ViewUsersPage;
import org.apache.wicket.MarkupContainer;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.Panel;

public class AdminPanelPage extends Panel {

    public AdminPanelPage(final String componentName) {
        super(componentName);

        MarkupContainer list = new MarkupContainer("list") {
        };
        list.add(new Link<String>("delete-user") {

            @Override
            public void onClick() {
                setResponsePage(DeleteUserPage.class);
            }
        });
        list.add(new Link<String>("add-user") {

            @Override
            public void onClick() {
                setResponsePage(AddUserPage.class);
            }
        });
        list.add(new Link<String>("view-user") {

            @Override
            public void onClick() {
                setResponsePage(ViewUsersPage.class);
            }
        });
        list.add(new Link<String>("add-flight") {

            @Override
            public void onClick() {
                setResponsePage(AddFlightPage.class);
            }
        });
        list.add(new Link<String>("view-flight") {

            @Override
            public void onClick() {
                setResponsePage(ViewFlightsPage.class);
            }
        });
        list.add(new Link<String>("menu-admin") {

            @Override
            public void onClick() {
                setResponsePage(MenuAdminPage.class);
            }
        });
        add(list);
        add(new MarkupContainer("body") {
        });
    }

}