package ir.dotin.qc.ticketReserve.view;

/**
 * Created by niloofar on 11/8/14.
 */

import ir.dotin.qc.ticketReserve.gateway.FlightGateway;
import ir.dotin.qc.ticketReserve.gateway.ReserveGateway;
import ir.dotin.qc.ticketReserve.model.Flight;
import ir.dotin.qc.ticketReserve.model.Reserve;
import org.apache.wicket.Session;
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
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


public class ReserveFlight extends WebPage implements Serializable {
    FlightGateway flightGateway =new FlightGateway();
    ReserveGateway reserveGateway = new ReserveGateway();
    public ReserveFlight(final PageParameters parameters) {
        Session session = getSession();
        Boolean login = (Boolean) session.getAttribute("login");
        if (login) {
            List<Flight> result = new LinkedList<Flight>();
            add(new UserPanel("navomaticBorder1"));
            String destination = parameters.get("destination").toString();
            String orgin = parameters.get("orgin").toString();
            List<Flight> fi = flightGateway.flightList(destination, orgin);
            final RadioGroup<Flight> group = new RadioGroup<Flight>("group", new Model<Flight>());
            Form<?> form = new Form("form") {
                @Override
                protected void onSubmit() {
                    Flight fi = new Flight();
                    if (fi == null) {
                        info("لطفا گزینه مورد نظر خود را انتخاب کنید");
                    } else {
                        fi = (Flight) group.getDefaultModelObject();
                        System.out.println(group.getId());
                        String s = flightGateway.Reserve(fi.getId());
                        if (s.equalsIgnoreCase("Success")) {
                            info("رزرو با موفقیت انجام شد ");
                            Reserve reserve = new Reserve();
                            reserve.setFlightID(fi.getId());
                            Session session = getSession();
                            Integer in = (Integer) session.getAttribute("USERID");
                            reserve.setUserID(in);
                            reserveGateway.save(reserve);
                        } else if (s.equalsIgnoreCase("Flight is Full")) {
                            info("‍‍‍‍‍ظرفیت پرواز مورد نظر به اتمام رسیده است");
                        } else {
                            info("انجام درخواست شما در حال حاضر امکان ‍ذیر نمی باشد");
                        }
                    }
                }
            };

            add(form);
            form.add(group);

            ListView<Flight> persons = new ListView<Flight>("persons", fi) {

                @Override
                protected void populateItem(ListItem<Flight> item) {
                    item.add(new Radio<Flight>("radio", item.getModel()));
                    item.add(new Label("id", new PropertyModel<String>(item.getDefaultModel(),
                            "id")));
                    item.add(new Label("name",
                            new PropertyModel<String>(item.getDefaultModel(), "destination_id")));
                    item.add(new Label("lastName", new PropertyModel<String>(item.getDefaultModel(),
                            "orgin_id")));
                    item.add(new Label("sth", new PropertyModel<String>(item.getDefaultModel(),
                            "name")));
                }

            };

            group.add(persons);

            add(new FeedbackPanel("feedback"));
        }
    }
    }
