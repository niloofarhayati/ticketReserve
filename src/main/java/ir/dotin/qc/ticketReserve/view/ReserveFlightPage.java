package ir.dotin.qc.ticketReserve.view;

/**
 * Created by niloofar on 11/8/14.
 */

import ir.dotin.qc.ticketReserve.gateway.FlightGateway;
import ir.dotin.qc.ticketReserve.gateway.ReserveGateway;
import ir.dotin.qc.ticketReserve.model.Flight;
import ir.dotin.qc.ticketReserve.model.Reserve;
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
import java.util.List;


public class ReserveFlightPage extends WebPage implements Serializable {
    private FlightGateway flightGateway =new FlightGateway();
    private ReserveGateway reserveGateway = new ReserveGateway();

    public ReserveFlightPage(final PageParameters parameters) {

//        Session session = getSession();
//        Boolean login = (Boolean) session.getAttribute("login");
        ExtendedSession extendedSession=ExtendedSession.get();
        Boolean login=extendedSession.getLogined();
        if (login) {
            add(new UserPanelPage("userPanel"));
            String destination = parameters.get("destination").toString();
            String orgin = parameters.get("orgin").toString();
            List<Flight> flightList = flightGateway.flightList(destination, orgin);
            final RadioGroup<Flight> flightRadioGroup = new RadioGroup<Flight>("flightRadioGroup", new Model<Flight>());
            Form<?> form = new Form("form") {
                @Override
                protected void onSubmit() {
                    Flight flight = new Flight();
                    if (flight == null) {
                        info("لطفا گزینه مورد نظر خود را انتخاب کنید");
                    } else {
                        try {
                            flight = (Flight) flightRadioGroup.getDefaultModelObject();
                            String s = flightGateway.Reserve(flight.getId());
                            if (s.equalsIgnoreCase("Success")) {
                                info("رزرو با موفقیت انجام شد ");
                                Reserve reserve = new Reserve();
                                reserve.setFlightID(flight.getId());
                                ExtendedSession extendedSession=ExtendedSession.get();
                                Boolean login=extendedSession.getLogined();
                                Integer userID = (Integer) extendedSession.getUserID();
                                reserve.setUserID(userID);
                                reserveGateway.save(reserve);
                            } else if (s.equalsIgnoreCase("Flight is Full")) {
                                info("‍‍‍‍‍ظرفیت پرواز مورد نظر به اتمام رسیده است");
                            } else {
                                info("انجام درخواست شما در حال حاضر امکان ‍ذیر نمی باشد");
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            info("انجام درخواست شما در حال حاضر امکان پذیر نمی باشد");
                        }
                    }
                }
            };

            add(form);
            form.add(flightRadioGroup);

            ListView<Flight> flightListView = new ListView<Flight>("flightListView", flightList) {

                @Override
                protected void populateItem(ListItem<Flight> item) {
                    item.add(new Radio<Flight>("flight_radio", item.getModel()));
                    item.add(new Label("id", new PropertyModel<String>(item.getDefaultModel(),
                            "id")));
                    item.add(new Label("destination_id",
                            new PropertyModel<String>(item.getDefaultModel(), "destination_id")));
                    item.add(new Label("orgin_id", new PropertyModel<String>(item.getDefaultModel(),
                            "orgin_id")));
                    item.add(new Label("name", new PropertyModel<String>(item.getDefaultModel(),
                            "name")));
                }

            };

            flightRadioGroup.add(flightListView);

            add(new FeedbackPanel("feedback"));
        }
    }
    }
