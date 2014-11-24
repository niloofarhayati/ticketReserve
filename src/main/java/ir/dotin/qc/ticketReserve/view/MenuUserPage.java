package ir.dotin.qc.ticketReserve.view;

/**
 * Created by niloofar on 11/8/14.
 */

import ir.dotin.qc.ticketReserve.util.Constants;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;

import java.io.Serializable;


public class MenuUserPage extends WebPage implements Serializable {
    public MenuUserPage() {
        add(new UserPanelPage("userPanel"));
        Session session = getSession();
        Boolean login = (Boolean) session.getAttribute("login");
        if (login) {
            Form form = new Form("form");
            form.add(new Button("logoutButton") {
                @Override
                public void onSubmit() {
                    setResponsePage(LoginPage.class);
                    Session session = getSession();
                   session.clear();
                }
            });
            add(form);
        }
    }
}