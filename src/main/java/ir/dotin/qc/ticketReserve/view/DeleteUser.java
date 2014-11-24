package ir.dotin.qc.ticketReserve.view;

/**
 * Created by niloofar on 11/8/14.
 */

import ir.dotin.qc.ticketReserve.gateway.UserGateway;
import ir.dotin.qc.ticketReserve.model.User;
import ir.dotin.qc.ticketReserve.util.Constants;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

import java.io.Serializable;


public class DeleteUser extends WebPage implements Serializable {
    private Label label;
    private Label username;
    private TextField field;
    private User ad = new User();
    UserGateway am = new UserGateway();

    public DeleteUser() {
        add(new AdminPanel("navomaticBorder"));
        if (Constants.LOGIN) {
            Form form = new Form("form");
            field = new TextField("field", new Model(""));
            add(username = new Label("username", new Model("username")));
            form.add(field);
            form.add(username);
            form.add(new Button("button") {
                @Override
                public void onSubmit() {
                    String username = (String) field.getModelObject();
                    Integer in = am.findID(username);
                    Boolean b = am.destroy(in.toString(),User.class);
                    if (b) {
                        label.setDefaultModelObject("Successful");
                    } else {
                        label.setDefaultModelObject("Failure");
                    }
                    field.setModelObject("");
                }
            });
            add(form);
            add(label = new Label("message", new Model("")));
        }
    }
}