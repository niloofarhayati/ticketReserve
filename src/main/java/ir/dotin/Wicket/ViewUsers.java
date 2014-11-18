package ir.dotin.Wicket;

/**
 * Created by niloofar on 11/8/14.
 */

import ir.dotin.Manager.AdminManager;
import ir.dotin.Model.User;
import ir.dotin.utils.Constants;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.navigation.paging.PagingNavigator;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.repeater.Item;
import org.apache.wicket.markup.repeater.data.DataView;
import org.apache.wicket.markup.repeater.data.IDataProvider;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import java.io.Serializable;
import java.util.List;


public class ViewUsers extends WebPage implements Serializable {
    AdminManager am=new AdminManager();
    List <Integer> list;

    public ViewUsers() {

            final RadioGroup<User> group = new RadioGroup<User>("group", new Model<User>());
            Form<?> form = new Form("form") {
                @Override
                protected void onSubmit() {
                    group.getId();
                    info("selected person: " + group.getDefaultModelObjectAsString());
                }
            };

            add(form);
            form.add(group);

            ListView<User> persons = new ListView<User>("persons",am.list()) {

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