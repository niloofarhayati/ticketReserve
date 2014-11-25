package ir.dotin.qc.ticketReserve.view;

/**
 * Created by niloofar on 11/8/14.
 */

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;

import java.io.Serializable;


public class MenuAdminPage extends WebPage implements Serializable {
    public MenuAdminPage() {
        add(new AdminPanelPage("adminPanel"));
//        Session session = getSession();
//        Boolean login = (Boolean) session.getAttribute("login");
        ExtendedSession extendedSession=ExtendedSession.get();
        Boolean login=extendedSession.getLogined();
        if (login) {
            Form menuAdminForm = new Form("menuAdminForm");
            menuAdminForm.add(new Button("logoutButton") {
                @Override
                public void onSubmit() {
                    setResponsePage(LoginPage.class);
                    ExtendedSession extendedSession = ExtendedSession.get();
                    extendedSession.clear();
                }
            });
            add(menuAdminForm);
        }
    }

}