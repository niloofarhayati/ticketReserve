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


public class ViewReservesPage extends WebPage implements Serializable {
    private ReserveGateway reserveGateway;
    private FlightGateway flightGateway ;
    private Reserve reserve;

    public ViewReservesPage() {
        reserveGateway = new ReserveGateway();
        flightGateway =new FlightGateway();
    }
        @Override
        protected void onInitialize() {
            super.onInitialize();

        Session session = getSession();
        Boolean login = (Boolean) session.getAttribute("login");
        if (login) {
            add(new UserPanelPage("userPanel"));
            final RadioGroup<Reserve> reserveRadioGroup = new RadioGroup<Reserve>("reserveRadioGroup", new Model<Reserve>());
            Form<?> form = new Form("form") {
                @Override
                protected void onSubmit() {
                    reserve = (Reserve) reserveRadioGroup.getDefaultModelObject();
                }

            };
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
                            setResponsePage(ViewReservesPage.class);
                        } else
                            info("انجام درخواست شما در حال حاضر امکان پذیر نمی باشد");

                    }
                }
            };
            button2.setDefaultFormProcessing(false);
            form.add(button2);


            add(form);
            form.add(reserveRadioGroup);
            Integer userID = (Integer) session.getAttribute("USERID");
            ListView<Reserve> reserveListView = new ListView<Reserve>("reserveListView", reserveGateway.list(userID)) {

                @Override
                protected void populateItem(ListItem<Reserve> item) {
                    item.add(new Radio<Reserve>("reserveRadio", item.getModel()));
                    item.add(new Label("id", new PropertyModel<String>(item.getDefaultModel(),
                            "id")));
                    item.add(new Label("userID",
                            new PropertyModel<String>(item.getDefaultModel(), "userID")));
                    item.add(new Label("flightID", new PropertyModel<String>(item.getDefaultModel(),
                            "flightID")));
                }

            };

            reserveRadioGroup.add(reserveListView);

            add(new FeedbackPanel("feedback"));
        }
    }
}
