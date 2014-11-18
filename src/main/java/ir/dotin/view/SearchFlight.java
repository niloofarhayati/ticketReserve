package ir.dotin.view;

/**
 * Created by niloofar on 11/8/14.
 */

import ir.dotin.manager.CityManager;
import ir.dotin.manager.FlightManager;
import ir.dotin.model.City;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


public class SearchFlight extends WebPage implements Serializable {
    FlightManager flightManager = new FlightManager();
    CityManager cityManager = new CityManager();
    List<String> list=new LinkedList<String>();
    private static List<String> cities=new LinkedList<String>();
    private String selected = "";
    private String selected1 = "";

    public SearchFlight() {
//      List<City> ci= cityManager.list();
        for (City city : cityManager.list()) {
            System.out.println(city.getName());
            list.add(city.getName());
        }
        cities = list;
        add(new FeedbackPanel("feedback1"));

        DropDownChoice<String> listSites = new DropDownChoice<String>(
                "sites", new PropertyModel<String>(this, "selected"), cities);

        Form<?> form1 = new Form<Void>("form1") {
            @Override
            protected void onSubmit() {


            }
        };

        add(form1);
        form1.add(listSites);
        DropDownChoice<String> listSites1 = new DropDownChoice<String>(
                "sites2", new PropertyModel<String>(this, "selected1"), cities);

        Form<?> form2 = new Form<Void>("form2") {
            @Override
            protected void onSubmit() {
                info("Selected search engine : " + selected1);
              //  List<Flight> fi=  flightManager.flightList(selected,selected1);
                PageParameters params = new PageParameters();
                params.add("orgin", selected);
                params.add("destination", selected1);
                setResponsePage(ReserveFlight.class,params);

            }
        };

        add(form2);
        form2.add(listSites1);



//        final RadioGroup<Flight> group = new RadioGroup<Flight>("group", new Model<Flight>());
//        Form<?> form = new Form("form") {
//            @Override
//            protected void onSubmit() {
//                String s = flightManager.Reserve(Integer.parseInt(group.getId()));
//                if (s.equalsIgnoreCase("Success"))
//                    info("رزرو با موفقیت انجام شد ");
//                else if (s.equalsIgnoreCase("Flight is Full")) {
//                    info("‍‍‍‍‍ظرفیت پرواز مورد نظر به اتمام رسیده است");
//                } else {
//                    info("انجام درخواست شما در حال حاضر امکان ‍ذیر نمی باشد");
//                }
//            }
//        };
//
//        add(form);
//        form.add(group);
//
//        ListView<Flight> persons = new ListView<Flight>("persons", fi) {
//
//            @Override
//            protected void populateItem(ListItem<Flight> item) {
//                item.add(new Radio<Flight>("radio", item.getModel()));
//                item.add(new Label("id", new PropertyModel<String>(item.getDefaultModel(),
//                        "id")));
//                item.add(new Label("name",
//                        new PropertyModel<String>(item.getDefaultModel(), "destination_id")));
//                item.add(new Label("lastName", new PropertyModel<String>(item.getDefaultModel(),
//                        "orgin_id")));
//            }
//
//        };
//
//        group.add(persons);
//
//        add(new FeedbackPanel("feedback"));
    }
}


