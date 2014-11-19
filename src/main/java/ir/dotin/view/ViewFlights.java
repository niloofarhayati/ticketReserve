package ir.dotin.view;

/**
 * Created by niloofar on 11/8/14.
 */

import ir.dotin.manager.AdminManager;
import ir.dotin.manager.FlightManager;
import ir.dotin.model.Flight;
import ir.dotin.model.User;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import java.io.Serializable;
import java.util.List;


public class ViewFlights extends WebPage implements Serializable {
    FlightManager flightManager = new FlightManager();

    public ViewFlights() {

        final RadioGroup<Flight> group = new RadioGroup<Flight>("group", new Model<Flight>());
        Form<?> form = new Form("form") {
            @Override
            protected void onSubmit() {
                Flight flight=(Flight)group.getDefaultModelObject();

                info("selected person: " + group.getDefaultModelObjectAsString());
            }
        };

        add(form);
        form.add(group);
        ListView<Flight> persons = new ListView<Flight>("persons", flightManager.list()) {

            @Override
            protected void populateItem(ListItem<Flight> item) {
                item.add(new Radio<Flight>("radio", item.getModel()));
                item.add(new Label("id", new PropertyModel<String>(item.getDefaultModel(),
                        "name")));
                item.add(new Label("name",
                        new PropertyModel<String>(item.getDefaultModel(), "orgin_id")));
                item.add(new Label("lastName", new PropertyModel<String>(item.getDefaultModel(),
                        "destination_id")));
                item.add(new Label("capacity", new PropertyModel<String>(item.getDefaultModel(),
                        "capacity")));
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