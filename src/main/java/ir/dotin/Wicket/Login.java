package ir.dotin.Wicket;

/**
 * Created by niloofar on 11/8/14.
 */

import ir.dotin.Manager.AdminManager;
import ir.dotin.utils.Constants;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
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
    AdminManager am=new AdminManager();
    public  Login() {
     //   add(new NavomaticBorder("navomaticBorder"));
        Form form = new Form("form");
        field = new TextField("field", new Model(""));
        field1 = new TextField("field1", new Model(""));
        add(username = new Label("username", new Model("username:")));
        add(password = new Label("password", new Model("password:")));
        form.add(field);
        form.add(username);
        form.add(field1);
        form.add(password);
        form.add(new Button("button") {
            @Override
            public void onSubmit() {
                String value = (String)field.getModelObject();
                String value1 = (String)field1.getModelObject();
                Integer b=am.Login(value,value1);
                Boolean bo=am.IsAdmin(b.toString());
                if(b!=null&&bo){
                    label.setDefaultModelObject("login Successful");
                   Constants.LOGIN=true;
                    PageParameters params = new PageParameters();
                    params.add("login", "true");
                    setResponsePage(NavomaticBorder.class,params);
                }
                 else if(b!=null&&!bo){
                    label.setDefaultModelObject("login Successful");
                    Constants.LOGIN=true;
                    PageParameters params = new PageParameters();
                    params.add("login", "true");
                    setResponsePage(NavomaticBorder.class,params);
                }
                else{
                    label.setDefaultModelObject("wrong username or password");
                    Constants.LOGIN=false;
                }
                field.setModelObject("");
                field1.setModelObject("");
            }
        });
        add(form);
        add(label = new Label("message", new Model("")));
    }
    }