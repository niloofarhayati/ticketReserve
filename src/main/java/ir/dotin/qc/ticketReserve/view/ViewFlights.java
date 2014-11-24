package ir.dotin.qc.ticketReserve.view;

/**
 * Created by niloofar on 11/8/14.
 */

import ir.dotin.qc.ticketReserve.gateway.FlightGateway;
import ir.dotin.qc.ticketReserve.model.Flight;
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


public class ViewFlights extends WebPage implements Serializable {
    FlightGateway flightGateway = new FlightGateway();
    Flight flight;

    public ViewFlights() {
        Session session = getSession();
        Boolean login = (Boolean) session.getAttribute("login");
        if (login) {
            add(new AdminPanel("navomaticBorder"));
            final RadioGroup<Flight> group = new RadioGroup<Flight>("group", new Model<Flight>());
            Form<?> form = new Form("form") {
                @Override
                protected void onSubmit() {
                    flight = (Flight) group.getDefaultModelObject();
//
//                info("selected person: " + group.getDefaultModelObjectAsString());
                }

            };
            Button button1 = new Button("update") {
                @Override
                public void onSubmit() {
                    if(flight==null)
                        info("لطفا گزینه مورد نظر را ثبت یا انتخاب کنید");
                    else {
                        PageParameters params = new PageParameters();
                        params.add("flight", flight.getId());
                        setResponsePage(UpdateFlight.class, params);
                    }
                }
            };
            form.add(button1);

            Button button2 = new Button("delete") {
                @Override
                public void onSubmit() {
                    if (flight == null)
                        info("لطفا گزینه مورد نظر را ثبت یا انتخاب کنید");
                    else {
                        Boolean b = flightGateway.destroy(flight.getId().toString(), Flight.class);
                        if (b) {
                            info("حذف با موفقیت  انجام شد");
                            setResponsePage(ViewFlights.class);
                        } else
                            info("انجام درخواست شما در حال حاضر امکان پذیر نمی باشد");

                    }
                }
            };
            button2.setDefaultFormProcessing(false);
            form.add(button2);


            add(form);
            form.add(group);
            ListView<Flight> persons = new ListView<Flight>("persons", flightGateway.list()) {

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
}
