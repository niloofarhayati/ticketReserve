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
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.io.Serializable;
import java.util.List;


public class ViewUsersPage extends WebPage implements Serializable {
    UserGateway userGateway = new UserGateway();
    List<User> userList;
    User user;

    public ViewUsersPage() {
    }
    @Override
    protected void onInitialize() {
        super.onInitialize();

        Session session = getSession();
        Boolean login = (Boolean) session.getAttribute("login");
        if (login) {
            add(new AdminPanelPage("adminPanel"));
            final RadioGroup<User> userRadioGroup = new RadioGroup<User>("userRadioGroup", new Model<User>());
            Form<?> form = new Form("form") {
                @Override
                protected void onSubmit() {
                    user = (User) userRadioGroup.getDefaultModelObject();
                   // info("selected person: " + userRadioGroup.getDefaultModelObjectAsString());
                }
            };
            Button button1 = new Button("update") {
                @Override
                public void onSubmit() {
                    if(user==null){
                        info("لطفا گزینه مورد نظر خود را انتخاب کنید");
                    }
                    else {
                        PageParameters params = new PageParameters();
                        params.add("user", user.getId());
                        setResponsePage(UpdateUserPage.class, params);
                    }
                }
            };
            form.add(button1);

            Button button2 = new Button("delete") {
                @Override
                public void onSubmit() {
                    if (user == null) {
                        info("لطفا گزینه مورد نظر خود را انتخاب کنید");
                    } else {
                        Boolean b = userGateway.destroy(user.getId().toString(), User.class);
                        if (b) {
                            info("حذف با موفقیت  انجام شد");
                            setResponsePage(ViewUsersPage.class);
                        } else
                            info("انجام درخواست شما در حال حاضر امکان پذیر نمی باشد");

                    }
                }
            };
            button2.setDefaultFormProcessing(false);
            form.add(button2);

            add(form);
            form.add(userRadioGroup);
            ListView<User> userListView = new ListView<User>("userListView", userGateway.list()) {

                @Override
                protected void populateItem(ListItem<User> item) {
                    item.add(new Radio<User>("radioUser", item.getModel()));
                    item.add(new Label("id", new PropertyModel<String>(item.getDefaultModel(),
                            "id")));
                    item.add(new Label("name",
                            new PropertyModel<String>(item.getDefaultModel(), "username")));
                    item.add(new Label("lastName", new PropertyModel<String>(item.getDefaultModel(),
                            "last_name")));
                }

            };

            userRadioGroup.add(userListView);

            add(new FeedbackPanel("feedback"));
        }
    }
}
