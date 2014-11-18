package ir.dotin.view;

/**
 * Created by niloofar on 11/8/14.
 */

import ir.dotin.util.Constants;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;

import java.io.Serializable;


public class MenuUser extends WebPage implements Serializable {
    public MenuUser() {
        add(new NavomaticBorder1("navomaticBorder1"));
        if (Constants.LOGIN) {
            Form form = new Form("form");
            form.add(new Button("button") {
                @Override
                public void onSubmit() {
                    Constants.LOGIN = false;
                }
            });
            add(form);
        }
    }
}