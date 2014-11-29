package ir.dotin.qc.ticketReserve.viewUtils;

/**
 * Created by niloofar on 11/8/14.
 */

import ir.dotin.qc.ticketReserve.user.LoginPage;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;

import java.io.Serializable;


public class MenuUserPage extends WebPage implements Serializable {
    public MenuUserPage() {
    }
    @Override
    protected void onInitialize() {
        super.onInitialize();
       add(new UserPanelPage("userPanel"));
//        Session session = getSession();
//        Boolean login = (Boolean) session.getAttribute("login");
        ExtendedSession extendedSession=ExtendedSession.get();
        Boolean login=extendedSession.getLogined();
        if (login) {
            Form menuUserForm = new Form("menuUserForm");
            menuUserForm.add(new Button("logoutButton") {
                @Override
                public void onSubmit() {
                    setResponsePage(LoginPage.class);
                    ExtendedSession extendedSession = ExtendedSession.get();
                    extendedSession.clear();
                }
            });
            add(menuUserForm);
        }
    }
}