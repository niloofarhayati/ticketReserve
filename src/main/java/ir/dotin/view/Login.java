package ir.dotin.view;

/**
 * Created by niloofar on 11/8/14.
 */

import ir.dotin.manager.AdminManager;
import ir.dotin.util.Constants;
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


public class Login extends WebPage implements Serializable {
    private Label label;
    private Label username;
    private Label password;
    private TextField field;
    private TextField field1;
    AdminManager am = new AdminManager();

    public Login() {
        //   add(new NavomaticBorder("navomaticBorder"));
        Form form = new Form("form");
        field = new TextField("field", new Model(""));
        field1 = new PasswordTextField("field1", new Model(""));
        add(username = new Label("username", new Model("username:")));
        add(password = new Label("password", new Model("password:")));
        form.add(field);
        form.add(username);
        form.add(field1);
        form.add(password);
        form.add(new Button("button") {
            @Override
            public void onSubmit() {
                String value = (String) field.getModelObject();
                String value1 = (String) field1.getModelObject();
                Integer b = am.Login(value, value1);
                if (b != null) {
                    Boolean bo = am.IsAdmin(b);
                    if (bo) {
                        label.setDefaultModelObject("login Successful");
                        Constants.LOGIN = true;
                        Session session = getSession();
                        session.setAttribute("userID", b);
                        session.setAttribute("login",Constants.LOGIN);
                        PageParameters params = new PageParameters();
                        params.add("login", "true");
                        setResponsePage(MenuAdmin.class, params);
                    } else if (!bo) {
                        label.setDefaultModelObject("login Successful");
                        Constants.LOGIN = true;
                        Session session = getSession();
                        session.setAttribute("USERID", b);
                        session.setAttribute("login",Constants.LOGIN);
                        PageParameters params = new PageParameters();
                        params.add("login", "true");
                        setResponsePage(MenuUser.class, params);
                    }
                } else {
                    label.setDefaultModelObject("wrong username or password");
                    Constants.LOGIN = false;
                }
                field.setModelObject("");
                field1.setModelObject("");
            }
        });
        add(form);
        add(label = new Label("message", new Model("")));
    }
}