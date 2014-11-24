package ir.dotin.qc.ticketReserve.view;

/**
 * Created by niloofar on 11/8/14.
 */

import ir.dotin.qc.ticketReserve.gateway.UserGateway;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

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
//                        ExtendedSession extendedSession=ExtendedSession.get();
//                        extendedSession.setUserID(loginStatus);
//                        extendedSession.setLogined(true);
                        Session session = getSession();
                        session.setAttribute("userID", loginStatus);
                        session.setAttribute("login", true);
                        PageParameters params = new PageParameters();
                        params.add("login", "true");
                        setResponsePage(new MenuAdminPage());
                    } else {
                        feedbackLabel.setDefaultModelObject("login Successful");
                        Session session = getSession();
                        session.setAttribute("USERID", loginStatus);
                        session.setAttribute("login", true);
                        PageParameters params = new PageParameters();
                        params.add("login", "true");
                        setResponsePage(MenuUserPage.class, params);
                    }
                } catch(Exception e) {
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