package ir.dotin.qc.ticketReserve.view;

/**
 * Created by niloofar on 11/8/14.
 */

import ir.dotin.qc.ticketReserve.gateway.UserGateway;
import ir.dotin.qc.ticketReserve.model.User;
import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

import java.io.Serializable;


public class AddUser extends WebPage implements Serializable {
    private Label message;
    private Label username;
    private Label password;
    private Label firstName;
    private Label lastName;
    private Label type;
    private TextField usernameFiled;
    private TextField passwordField;
    private TextField firstNameField;
    private TextField lastNameField;
    private NumberTextField typeField;
    private User ad = new User();
    UserGateway am = new UserGateway();

    public AddUser() {
        add(new AdminPanel("navomaticBorder"));
        Session session = getSession();
        Boolean login = (Boolean) session.getAttribute("login");
        if (login) {
            Form form = new Form("form");
            usernameFiled = new TextField("username_filed", new Model(""));
            passwordField = new TextField("password_field", new Model(""));
            firstNameField = new TextField("firstName_field", new Model(""));
            lastNameField = new TextField("lastName_field", new Model(""));
            typeField = new NumberTextField("type_field", new Model(1));
            add(username = new Label("username", new Model("username")));
            add(password = new Label("password", new Model("password")));
            add(firstName = new Label("firstName", new Model("first Name")));
            add(lastName = new Label("lastName", new Model("last Name")));
            add(type = new Label("type", new Model("type")));
            form.add(usernameFiled);
            form.add(username);
            form.add(passwordField);
            form.add(password);
            form.add(firstNameField);
            form.add(firstName);
            form.add(lastNameField);
            form.add(lastName);
            form.add(type);
            form.add(typeField);
            form.add(new Button("addUserButton") {
                @Override
                public void onSubmit() {
                    String username = (String) usernameFiled.getModelObject();
                    Integer in = am.findID(username);
                    if (in != null) {
                        message.setDefaultModelObject
                                ("این نام کاربری تکراری می باشد لطفا دوباره انتخاب کنید");
                    } else {

                        String pass = (String) passwordField.getModelObject();
                        String first = (String) firstNameField.getModelObject();
                        String last = (String) lastNameField.getModelObject();
                        Integer ty = (Integer) typeField.getModelObject();
                        Boolean b = am.createAndStoreAdmin(last, first, pass, username, ty);
                        if (b) {
                            message.setDefaultModelObject("اضافه کردن کاربر با موفقیت انجام شد");
                        } else {
                            message.setDefaultModelObject("انجام درخواست شمادر حال حاضر امکان پذیر نمی باشد");
                        }
                        usernameFiled.setModelObject("");
                        passwordField.setModelObject("");
                        firstNameField.setModelObject("");
                        lastNameField.setModelObject("");
                        typeField.setModelObject(1);
                    }
                }
            });
            add(form);
            add(message = new Label("message", new Model("")));
        }
    }
}