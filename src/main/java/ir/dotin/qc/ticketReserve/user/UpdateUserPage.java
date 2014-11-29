package ir.dotin.qc.ticketReserve.user;

/**
 * Created by niloofar on 11/8/14.
 */

import ir.dotin.qc.ticketReserve.gateway.UserGateway;
import ir.dotin.qc.ticketReserve.viewUtils.AdminPanelPage;
import ir.dotin.qc.ticketReserve.viewUtils.ExtendedSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.io.Serializable;


public class UpdateUserPage extends WebPage implements Serializable {
    private Label message;
    private TextField usernameFiled;
    private TextField passwordField;
    private TextField firstNameField;
    private TextField lastNameField;
    private NumberTextField typeField;
    private UserGateway userGateway ;
    private PageParameters parameters;

    public UpdateUserPage(final PageParameters parameters) {
        userGateway = new UserGateway();
        this.parameters=parameters;
    }
    @Override
    protected void onInitialize() {
        super.onInitialize();
        ExtendedSession extendedSession=ExtendedSession.get();
        Boolean login=extendedSession.getLogined();
        if (login) {
            final Integer in = parameters.get("user").toInt();
            add(new AdminPanelPage("adminPanel"));
                Form updateUserForm = new Form("updateUserForm");
                usernameFiled = new TextField("username_field", new Model(""));
            Label username;
            add(username = new Label("username", new Model("username")));
                passwordField = new TextField("password_field", new Model(""));
            Label password;
            add(password = new Label("password", new Model("password")));
                firstNameField = new TextField("firstName_field", new Model(""));
            Label firstName;
            add(firstName = new Label("firstName", new Model("first Name")));
                lastNameField = new TextField("lastName_field", new Model(""));
            Label lastName;
            add(lastName = new Label("lastName", new Model("last Name")));
                typeField = new NumberTextField("type_field", new Model(1));
            Label type;
            add(type = new Label("type", new Model("type")));
                updateUserForm.add(usernameFiled);
                updateUserForm.add(username);
                updateUserForm.add(passwordField);
                updateUserForm.add(password);
                updateUserForm.add(firstNameField);
                updateUserForm.add(firstName);
                updateUserForm.add(lastNameField);
                updateUserForm.add(lastName);
                updateUserForm.add(typeField);
                updateUserForm.add(type);
                updateUserForm.add(new Button("updateButton") {
                    @Override
                    public void onSubmit() {
                        try {
                            String username = (String) usernameFiled.getModelObject();
                            String pass = (String) passwordField.getModelObject();
                            String first = (String) firstNameField.getModelObject();
                            String last = (String) lastNameField.getModelObject();
                            Integer ty = (Integer) typeField.getModelObject();
                            Boolean b = userGateway.update(in.toString(), first, last, username, pass, ty);
                            if (b) {
                                message.setDefaultModelObject("به روز رسانی با موفقیت انجام شد");
                            } else {
                                message.setDefaultModelObject("انجام درخواست شمادر حال حاضر امکان پذیر نمی باشد");
                            }
                            usernameFiled.setModelObject("");
                            passwordField.setModelObject("");
                            firstNameField.setModelObject("");
                            lastNameField.setModelObject("");
                            typeField.setModelObject(1);
                        } catch (Exception e) {
                            e.printStackTrace();
                            info("انجام درخواست شما در حال حاضر امکان پذیر نمی باشد");
                        }
                    }
                });
                add(updateUserForm);
                add(message = new Label("message", new Model("")));
        }
    }
}