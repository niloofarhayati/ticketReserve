package ir.dotin.Wicket;

/**
 * Created by niloofar on 11/8/14.
 */

import ir.dotin.Manager.AdminManager;
import ir.dotin.Model.User;
import ir.dotin.utils.Constants;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.Model;

import java.io.Serializable;


public class AddUser extends WebPage implements Serializable {
    private Label label;
    private Label username;
    private Label password;
    private Label firstName;
    private Label lastName;
    private TextField field;
    private TextField field1;
    private TextField field2;
    private TextField field3;
    private User ad=new User();
    AdminManager am=new AdminManager();
    public AddUser() {
        add(new NavomaticBorder("navomaticBorder"));
        if(Constants.LOGIN) {
            Form form = new Form("form");
            field = new TextField("field", new Model(""));
            field1 = new TextField("field1", new Model(""));
            field2 = new TextField("field2", new Model(""));
            field3 = new TextField("field3", new Model(""));
            add(username = new Label("username", new Model("username")));
            add(password = new Label("password", new Model("password")));
            add(firstName = new Label("firstName", new Model("first Name")));
            add(lastName = new Label("lastName", new Model("last Name")));
            form.add(field);
            form.add(username);
            form.add(field1);
            form.add(password);
            form.add(field2);
            form.add(firstName);
            form.add(field3);
            form.add(lastName);
            form.add(new Button("button") {
                @Override
                public void onSubmit() {
                    String username = (String) field.getModelObject();
                    String pass = (String) field1.getModelObject();
                    String first = (String) field1.getModelObject();
                    String last = (String) field1.getModelObject();
                    ad.setFirst_name(first);
                    ad.setLast_name(last);
                    ad.setUsername(username);
                    ad.setPassword(pass);
                    Boolean b = am.save(ad);
                    if (b) {
                        label.setDefaultModelObject("Successful");
                    } else {
                        label.setDefaultModelObject("Failure");
                    }
                    field.setModelObject("");
                    field1.setModelObject("");
                    field2.setModelObject("");
                    field3.setModelObject("");
                }
            });
            add(form);
            add(label = new Label("message", new Model("")));
        }
        else{
            Form form = new Form("form");
            field = new TextField("field", new Model(""));
            field1 = new TextField("field1", new Model(""));
            field2 = new TextField("field2", new Model(""));
            field3 = new TextField("field3", new Model(""));
            add(username = new Label("username", new Model("username")));
            add(password = new Label("password", new Model("password")));
            add(firstName = new Label("firstName", new Model("first Name")));
            add(lastName = new Label("lastName", new Model("last Name")));
            form.add(field);
            form.add(username);
            form.add(field1);
            form.add(password);
            form.add(field2);
            form.add(firstName);
            form.add(field3);
            form.add(lastName);
            form.add(new Button("button") {
                @Override
                public void onSubmit() {
                    String username = (String) field.getModelObject();
                    String pass = (String) field1.getModelObject();
                    String first = (String) field1.getModelObject();
                    String last = (String) field1.getModelObject();
                    field.setModelObject("");
                    field1.setModelObject("");
                    field2.setModelObject("");
                    field3.setModelObject("");
                }
            });
            add(form);
            add(label = new Label("message", new Model("Please Login")));
        }
    }
    }