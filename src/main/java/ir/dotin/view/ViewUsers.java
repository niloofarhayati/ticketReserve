package ir.dotin.view;

/**
 * Created by niloofar on 11/8/14.
 */

import ir.dotin.manager.AdminManager;
import ir.dotin.model.Flight;
import ir.dotin.model.User;
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


public class ViewUsers extends WebPage implements Serializable {
    AdminManager am = new AdminManager();
    List<User> list;
    User user;

    public ViewUsers() {
        Session session = getSession();
        Boolean login = (Boolean) session.getAttribute("login");
        if (login) {
            add(new NavomaticBorder("navomaticBorder"));
            final RadioGroup<User> group = new RadioGroup<User>("group", new Model<User>());
            Form<?> form = new Form("form") {
                @Override
                protected void onSubmit() {
                    user = (User) group.getDefaultModelObject();
                   // info("selected person: " + group.getDefaultModelObjectAsString());
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
                        setResponsePage(UpdateUser.class, params);
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
                        Boolean b = am.destroy(user.getId().toString(), User.class);
                        if (b) {
                            info("حذف با موفقیت  انجام شد");
                            setResponsePage(ViewUsers.class);
                        } else
                            info("انجام درخواست شما در حال حاضر امکان پذیر نمی باشد");

                    }
                }
            };
            button2.setDefaultFormProcessing(false);
            form.add(button2);

            add(form);
            form.add(group);
            ListView<User> persons = new ListView<User>("persons", am.list()) {

                @Override
                protected void populateItem(ListItem<User> item) {
                    item.add(new Radio<User>("radio", item.getModel()));
                    item.add(new Label("id", new PropertyModel<String>(item.getDefaultModel(),
                            "id")));
                    item.add(new Label("name",
                            new PropertyModel<String>(item.getDefaultModel(), "username")));
                    item.add(new Label("lastName", new PropertyModel<String>(item.getDefaultModel(),
                            "last_name")));
                }

            };

            group.add(persons);

            add(new FeedbackPanel("feedback"));
        }
    }
}
//        add(new NavomaticBorder("navomaticBorder"));
//        final IDataProvider<User> li= new CatapultListDataProvider(am.list());
//
//        DataView<User> dataView=new DataView<User>("rows",li,10l){
//            @Override protected void populateItem(Item<User> item){
//                final User user=item.getModelObject();
//                item.setModel(new CompoundPropertyModel<User>(user));
//                item.add(new Label("id"));
//                item.add(new Label("first_name"));
//                item.add(new Label("last_name"));
//                item.add(new Label("username"));
//                item.add(new Link<User>("delete",item.getModel()){
//                             @Override
//                             public void onClick(){
//                                 am.destroy(user.getId().toString());
//                             }
//                         }
//                );
//            }
//        }
//                ;
//        add(dataView);
//       // add(new PagingNavigator("footerPaginator",dataView));
//    }
//
////        add(new ListView<User>("rows",li)
////        {
////            public void populateItem(ListItem<User> item)
////            {
////                int i=0;
////                item.add(new Label("fullName",
////                        new PropertyModel(item.getModel(),"username")),new Label("fullName2",
////                        new PropertyModel(item.getModel(),"username")));
////                //item= (ListItem<User>) am.list();
////                i++;
////
////            }
////        });}
//  }