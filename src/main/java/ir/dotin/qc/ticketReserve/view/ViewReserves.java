package ir.dotin.qc.ticketReserve.view;

/**
 * Created by niloofar on 11/8/14.
 */

import ir.dotin.qc.ticketReserve.gateway.FlightGateway;
import ir.dotin.qc.ticketReserve.gateway.ReserveGateway;
import ir.dotin.qc.ticketReserve.model.Reserve;
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

import java.io.Serializable;


public class ViewReserves extends WebPage implements Serializable {
    ReserveGateway reserveGateway = new ReserveGateway();
    FlightGateway flightGateway =new FlightGateway();
    Reserve reserve;

    public ViewReserves() {
        Session session = getSession();
        Boolean login = (Boolean) session.getAttribute("login");
        if (login) {
            add(new UserPanel("navomaticBorder"));
            final RadioGroup<Reserve> group = new RadioGroup<Reserve>("group", new Model<Reserve>());
            Form<?> form = new Form("form") {
                @Override
                protected void onSubmit() {
                    reserve = (Reserve) group.getDefaultModelObject();
//
//                info("selected person: " + group.getDefaultModelObjectAsString());
                }

            };
//            Button button1 = new Button("update") {
//                @Override
//                public void onSubmit() {
//                    if(reserve==null)
//                        info("لطفا گزینه مورد نظر را ثبت یا انتخاب کنید");
//                    else {
//                        PageParameters params = new PageParameters();
//                        params.add("Reserve", reserve.getId());
//                        setResponsePage(UpdateFlight.class, params);
//                    }
//                }
//            };
//            form.add(button1);

            Button button2 = new Button("delete") {
                @Override
                public void onSubmit() {
                    if (reserve == null)
                        info("لطفا گزینه مورد نظر را ثبت یا انتخاب کنید");
                    else {
                        Boolean b = reserveGateway.destroy(reserve.getId().toString(), Reserve.class);
                        flightGateway.UnReserve(reserve.getFlightID());
                        if (b) {
                            info("حذف با موفقیت  انجام شد");
                            setResponsePage(ViewReserves.class);
                        } else
                            info("انجام درخواست شما در حال حاضر امکان پذیر نمی باشد");

                    }
                }
            };
            button2.setDefaultFormProcessing(false);
            form.add(button2);


            add(form);
            form.add(group);
            Integer in = (Integer) session.getAttribute("USERID");
            ListView<Reserve> persons = new ListView<Reserve>("persons", reserveGateway.list(in)) {

                @Override
                protected void populateItem(ListItem<Reserve> item) {
                    item.add(new Radio<Reserve>("radio", item.getModel()));
                    item.add(new Label("id", new PropertyModel<String>(item.getDefaultModel(),
                            "id")));
                    item.add(new Label("name",
                            new PropertyModel<String>(item.getDefaultModel(), "userID")));
                    item.add(new Label("lastName", new PropertyModel<String>(item.getDefaultModel(),
                            "flightID")));
                }

            };

            group.add(persons);

            add(new FeedbackPanel("feedback"));
        }
    }
}