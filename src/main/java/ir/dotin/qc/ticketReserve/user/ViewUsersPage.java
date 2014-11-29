package ir.dotin.qc.ticketReserve.user;

/**
 * Created by niloofar on 11/8/14.
 */

import ir.dotin.qc.ticketReserve.gateway.UserGateway;
import ir.dotin.qc.ticketReserve.model.User;
import ir.dotin.qc.ticketReserve.viewUtils.AdminPanelPage;
import ir.dotin.qc.ticketReserve.viewUtils.ExtendedSession;
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

        ExtendedSession extendedSession=ExtendedSession.get();
        Boolean login=extendedSession.getLogined();
        if (login) {
            add(new AdminPanelPage("adminPanel"));
            final RadioGroup<User> userRadioGroup = new RadioGroup<User>("userRadioGroup", new Model<User>());
            Form<?> viewUserForm = new Form("viewUserForm") {
                @Override
                protected void onSubmit() {
                    user = (User) userRadioGroup.getDefaultModelObject();
                }
            };
            Button updateButton = new Button("update") {
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
            viewUserForm.add(updateButton);

            Button deleteButton = new Button("delete") {
                @Override
                public void onSubmit() {
                    if (user == null) {
                        info("لطفا گزینه مورد نظر خود را انتخاب کنید");
                    } else {
                        try {
                            Boolean b = userGateway.destroy(user.getId().toString(), User.class);
                            if (b) {
                                info("حذف با موفقیت  انجام شد");
                                setResponsePage(ViewUsersPage.class);
                            } else
                                info("انجام درخواست شما در حال حاضر امکان پذیر نمی باشد");

                        } catch (Exception e) {
                            e.printStackTrace();
                            info("انجام درخواست شما در حال حاضر امکان پذیر نمی باشد");
                        }
                    }
                }
            };
            deleteButton.setDefaultFormProcessing(false);
            viewUserForm.add(deleteButton);

            add(viewUserForm);
            viewUserForm.add(userRadioGroup);
            ListView<User> userListView = null;
            try {
                userListView = new ListView<User>("userListView", userGateway.list()) {

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
            } catch (Exception e) {
                e.printStackTrace();
                info("انجام درخواست شما در حال حاضر امکان پذیر نمی باشد");
            }

            userRadioGroup.add(userListView);

            add(new FeedbackPanel("feedback"));
        }
    }
}
