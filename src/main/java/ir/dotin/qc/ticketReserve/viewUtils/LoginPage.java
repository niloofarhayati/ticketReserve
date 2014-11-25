package ir.dotin.qc.ticketReserve.viewUtils;

/**
 * Created by niloofar on 11/8/14.
 */

import ir.dotin.qc.ticketReserve.gateway.UserGateway;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

import java.io.Serializable;


public class LoginPage extends WebPage implements Serializable {

    private TextField usernameField;
    private TextField passwordField;
    private Label feedbackLabel;

    private UserGateway userGateway;

   public LoginPage() {
        userGateway = new UserGateway();
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        Form loginForm = new Form("form");

        Label usernameLabel;
        add(usernameLabel = new Label("username-label", new Model("username:")));
        loginForm.add(usernameLabel);
        usernameField = new TextField("username-field", new Model(""));
        loginForm.add(usernameField);
        Label passwordLabel;
        add(passwordLabel = new Label("password-label", new Model("password:")));
        loginForm.add(passwordLabel);
        passwordField = new PasswordTextField("password-field", new Model(""));
        loginForm.add(passwordField);

        loginForm.add(new Button("submit-button") {
            @Override
            public void onSubmit() {
                String username = (String) usernameField.getModelObject();
                String password = (String) passwordField.getModelObject();
                try {
                    Integer loginStatus = userGateway.Login(username, password);
                    if (userGateway.IsAdmin(loginStatus)) {
                        feedbackLabel.setDefaultModelObject("login Successful");
                        ExtendedSession.get().setUserID(loginStatus);
                        ExtendedSession.get().setLogined(true);
//                        Session session = getSession();
//                        session.setAttribute("userID", loginStatus);
//                        session.setAttribute("login", true);
                        setResponsePage(new MenuAdminPage());
                    } else {
                        feedbackLabel.setDefaultModelObject("login Successful");
                        ExtendedSession.get().setUserID(loginStatus);
                        ExtendedSession.get().setLogined(true);
                        setResponsePage(MenuUserPage.class);
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                    feedbackLabel.setDefaultModelObject("wrong username or password");
                }
                usernameField.setModelObject("");
                passwordField.setModelObject("");
            }
        });
        add(loginForm);
        add(feedbackLabel = new Label("message", new Model("")));

    }
}