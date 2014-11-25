package ir.dotin.qc.ticketReserve.viewUser;

/**
 * Created by niloofar on 11/8/14.
 */

import ir.dotin.qc.ticketReserve.gateway.UserGateway;
import ir.dotin.qc.ticketReserve.model.User;
import ir.dotin.qc.ticketReserve.viewComponent.AdminPanelPage;
import ir.dotin.qc.ticketReserve.viewUtils.ExtendedSession;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

import java.io.Serializable;


public class AddUserPage extends WebPage implements Serializable {
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
    private User user = new User();
    UserGateway userGateway = new UserGateway();

    public AddUserPage() {
        user = new User();
        userGateway = new UserGateway();


    }
    @Override
    protected void onInitialize() {
        super.onInitialize();
        add(new AdminPanelPage("adminPanel"));
//        Session session = getSession();
//        Boolean login = (Boolean) session.getAttribute("login");
        ExtendedSession extendedSession=ExtendedSession.get();
        Boolean login=extendedSession.getLogined();
        if (login) {
            Form addUserForm = new Form("addUserForm");
            usernameFiled = new TextField("username_field", new Model(""));
            add(username = new Label("username", new Model("username")));
            passwordField = new TextField("password_field", new Model(""));
            add(password = new Label("password", new Model("password")));
            firstNameField = new TextField("firstName_field", new Model(""));
            add(firstName = new Label("firstName", new Model("first Name")));
            lastNameField = new TextField("lastName_field", new Model(""));
            add(lastName = new Label("lastName", new Model("last Name")));
            typeField = new NumberTextField("type_field", new Model(1));
            add(type = new Label("type", new Model("type")));
            addUserForm.add(usernameFiled);
            addUserForm.add(username);
            addUserForm.add(passwordField);
            addUserForm.add(password);
            addUserForm.add(firstNameField);
            addUserForm.add(firstName);
            addUserForm.add(lastNameField);
            addUserForm.add(lastName);
            addUserForm.add(typeField);
            addUserForm.add(type);
            addUserForm.add(new Button("addUserButton") {
                @Override
                public void onSubmit() {
                    try {
                        String username = (String) usernameFiled.getModelObject();
                        Integer userID = userGateway.findID(username);
                        if (userID != 0) {
                            message.setDefaultModelObject
                                    ("این نام کاربری تکراری می باشد لطفا دوباره انتخاب کنید");
                        } else {

                            String pass = (String) passwordField.getModelObject();
                            String first = (String) firstNameField.getModelObject();
                            String last = (String) lastNameField.getModelObject();
                            Integer ty = (Integer) typeField.getModelObject();
                            Boolean b = userGateway.createAndStoreAdmin(last, first, pass, username, ty);
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
                    } catch (Exception e) {
                        e.printStackTrace();
                        info("انجام درخواست شما در حال حاضر امکان پذیر نمی باشد");
                    }
                }
            });
            add(addUserForm);
            add(message = new Label("message", new Model("")));
        }
    }
}