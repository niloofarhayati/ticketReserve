package ir.dotin.qc.ticketReserve.view;

/**
 * Created by niloofar on 11/8/14.
 */

import ir.dotin.qc.ticketReserve.gateway.FlightGateway;
import ir.dotin.qc.ticketReserve.model.Flight;
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


public class ViewFlightsPage extends WebPage implements Serializable {
    private FlightGateway flightGateway ;
    private Flight flight;

    public ViewFlightsPage() {
        flightGateway = new FlightGateway();
    }
    @Override
    protected void onInitialize() {
        super.onInitialize();
        ExtendedSession extendedSession=ExtendedSession.get();
        Boolean login=extendedSession.getLogined();
        if (login) {
            add(new AdminPanelPage("adminPanel"));
            final RadioGroup<Flight> flightRadioGroup = new RadioGroup<Flight>("flightRadioGroup", new Model<Flight>());
            Form<?> form = new Form("form") {
                @Override
                protected void onSubmit() {
                    flight = (Flight) flightRadioGroup.getDefaultModelObject();
                }

            };
            form.add(new Button("update") {
                @Override
                public void onSubmit() {
                    if(flight==null)
                        info("لطفا گزینه مورد نظر را ثبت یا انتخاب کنید");
                    else {
                        PageParameters params = new PageParameters();
                        params.add("flight", flight.getId());
                        setResponsePage(UpdateFlightPage.class, params);
                    }
                }
            });

            Button deleteButton = new Button("delete") {
                @Override
                public void onSubmit() {
                    if (flight == null)
                        info("لطفا گزینه مورد نظر را ثبت یا انتخاب کنید");
                    else {
                        try {
                            Boolean b = flightGateway.destroy(flight.getId().toString(), Flight.class);
                            if (b) {
                                info("حذف با موفقیت  انجام شد");
                                setResponsePage(ViewFlightsPage.class);
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
            form.add(deleteButton);


            add(form);
            form.add(flightRadioGroup);
            ListView<Flight> flightListView = new ListView<Flight>("flightListView", flightGateway.list()) {

                @Override
                protected void populateItem(ListItem<Flight> item) {
                    item.add(new Radio<Flight>("flightRadio", item.getModel()));
                    item.add(new Label("name", new PropertyModel<String>(item.getDefaultModel(),
                            "name")));
                    item.add(new Label("orgin_id",
                            new PropertyModel<String>(item.getDefaultModel(), "orgin_id")));
                    item.add(new Label("destination_id", new PropertyModel<String>(item.getDefaultModel(),
                            "destination_id")));
                    item.add(new Label("capacity", new PropertyModel<String>(item.getDefaultModel(),
                            "capacity")));
                }

            };

            flightRadioGroup.add(flightListView);

            add(new FeedbackPanel("feedback"));
        }
    }
}
