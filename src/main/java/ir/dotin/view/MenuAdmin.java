package ir.dotin.view;

/**
 * Created by niloofar on 11/8/14.
 */

import ir.dotin.util.Constants;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;

import java.io.Serializable;


public class MenuAdmin extends WebPage implements Serializable {
    public MenuAdmin() {
        add(new NavomaticBorder("navomaticBorder"));
        if (Constants.LOGIN) {
            Form form = new Form("form");
            form.add(new Button("button") {
                @Override
                public void onSubmit() {
                    Constants.LOGIN = false;
                    setResponsePage(Login.class);
                    Session session = getSession();
                   session.clear();
                }
            });
            add(form);
        }
    }

}