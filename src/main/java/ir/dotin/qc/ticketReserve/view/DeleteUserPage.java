package ir.dotin.qc.ticketReserve.view;

/**
 * Created by niloofar on 11/8/14.
 */

import ir.dotin.qc.ticketReserve.gateway.UserGateway;
import ir.dotin.qc.ticketReserve.model.User;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

import java.io.Serializable;


public class DeleteUserPage extends WebPage implements Serializable {
    private Label message;
    private Label username;
    private TextField userNameField;
    private User ad = new User();
    UserGateway userGateway = new UserGateway();

    public DeleteUserPage() {
        add(new AdminPanelPage("adminPanel"));
//        Session session = getSession();
//        Boolean login = (Boolean) session.getAttribute("login");
        ExtendedSession extendedSession=ExtendedSession.get();
        Boolean login=extendedSession.getLogined();
        if (login) {
            Form form = new Form("form");
            userNameField = new TextField("username_field", new Model(""));
            add(username = new Label("username", new Model("username")));
            form.add(userNameField);
            form.add(username);
            form.add(new Button("deleteUserButton") {
                @Override
                public void onSubmit() {
                    try {
                        String username = (String) userNameField.getModelObject();
                        Integer in = userGateway.findID(username);
                        if(in!=0) {
                            Boolean b = userGateway.destroy(in.toString(), User.class);
                            if (b) {
                                message.setDefaultModelObject("درخواست شما با موفقیت انجام شد");
                            } else {
                                message.setDefaultModelObject("امکان انجام درخواست شما در حال حاضر امکان پذیر نمی باشد");
                            }
                        }
                        userNameField.setModelObject("");
                    } catch (Exception e) {
                        e.printStackTrace();
                        info("انجام درخواست شما در حال حاضر امکان پذیر نمی باشد");
                    }
                }
            });
            add(form);
            add(message = new Label("message", new Model("")));
        }
    }
}