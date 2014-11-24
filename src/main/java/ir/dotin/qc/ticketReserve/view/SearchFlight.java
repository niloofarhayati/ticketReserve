package ir.dotin.qc.ticketReserve.view;

/**
 * Created by niloofar on 11/8/14.
 */

import ir.dotin.qc.ticketReserve.gateway.CityGateway;
import ir.dotin.qc.ticketReserve.gateway.FlightGateway;
import ir.dotin.qc.ticketReserve.model.City;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
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
    FlightGateway flightGateway = new FlightGateway();
    CityGateway cityGateway = new CityGateway();
    List<String> list=new LinkedList<String>();
    private static List<String> cities=new LinkedList<String>();
    private String selected = "tehran";
    private String selected1 = "tehran";

    public SearchFlight() {
        Session session = getSession();
        Boolean login = (Boolean) session.getAttribute("login");
        if (login) {
            add(new UserPanel("navomaticBorder1"));
            for (City city : cityGateway.list()) {
                System.out.println(city.getName());
                list.add(city.getName());
            }
            cities = list;
            add(new FeedbackPanel("feedback1"));

            Form<?> form = new Form<Void>("form") {
                @Override
                protected void onSubmit() {

                    PageParameters params = new PageParameters();
                    params.add("orgin", selected);
                    params.add("destination", selected1);
                    setResponsePage(ReserveFlight.class, params);

                }
            };
            add(form);

            final DropDownChoice<String> makes = new DropDownChoice<String>("makes",
                    new PropertyModel<String>(this, "selected1"), cities);
            final DropDownChoice<String> city = new DropDownChoice<String>("city",
                    new PropertyModel<String>(this, "selected"), cities);
            form.add(city);
            form.add(makes);

            makes.add(new AjaxFormComponentUpdatingBehavior("onchange") {
                @Override
                protected void onUpdate(AjaxRequestTarget target) {
//                PageParameters params = new PageParameters();
//                params.add("orgin", selected);
//                params.add("destination", selected1);
//                setResponsePage(ReserveFlight.class,params);
                }
            });
            city.add(new AjaxFormComponentUpdatingBehavior("onchange") {
                @Override
                protected void onUpdate(AjaxRequestTarget target) {
                }
            });
//        final DropDownChoice<String> listSites1 = new DropDownChoice<String>(
//                "sites2", new PropertyModel<String>(this, "selected1"), cities);

//        Form<?> form2 = new Form<Void>("form2"){
//             @Override
//            protected void onSubmit() {
//              //  List<Flight> fi=  flightManager.flightList(selected,selected1);
//                PageParameters params = new PageParameters();
//                params.add("orgin", selected);
//                params.add("destination", selected1);
//                setResponsePage(ReserveFlight.class,params);
//
//            }
//        };
//
//        add(form2);
//        form2.add(listSites1);


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
}


