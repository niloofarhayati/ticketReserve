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
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;

import java.io.Serializable;
import java.util.List;


public class ViewUsers extends WebPage implements Serializable {
    private Label label;
    private Label username;
    private TextField field;
    private User ad=new User();
    AdminManager am=new AdminManager();
    List <Integer> list;

    public ViewUsers() {
      List<User>li=am.list();
        add(new ListView<User>("rows",li)
        {
            public void populateItem(ListItem<User> item)
            {
                item= (ListItem<User>) am.list();
            }
        });
        add(new NavomaticBorder("navomaticBorder"));
           Form form = new Form("form");
           field = new TextField("field", new Model(""));
            add(username = new Label("username", new Model("username")));
            form.add(field);
            label.setDefaultModelObject("نمایش کاربران");

            add(form);
    }
    }