package ir.dotin.Wicket;

/**
 * Created by niloofar on 11/8/14.
 */

import ir.dotin.Manager.AdminManager;
import ir.dotin.Manager.FlightManager;
import ir.dotin.Model.Flight;
import ir.dotin.Model.User;
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
    FlightManager flightManager=new FlightManager();
    List <Integer> list;

    public ReserveFlight(final PageParameters parameters) {
        List<Flight> result = new LinkedList<Flight>();

       String destination=parameters.get("destination").toString();
       String orgin= parameters.get("orgin").toString();
        List<Flight> fi=  flightManager.flightList(destination,orgin);
        final RadioGroup<Flight> group = new RadioGroup<Flight>("group", new Model<Flight>());
            Form<?> form = new Form("form") {
                @Override
                protected void onSubmit() {
                    String s=flightManager.Reserve(Integer.parseInt(group.getId()));
                    if(s.equalsIgnoreCase("Success"))
                    info("رزرو با موفقیت انجام شد ");
                    else if(s.equalsIgnoreCase("Flight is Full")){
                        info("‍‍‍‍‍ظرفیت پرواز مورد نظر به اتمام رسیده است");
                    }
                    else{
                        info("انجام درخواست شما در حال حاضر امکان ‍ذیر نمی باشد");
                    }
                }
            };

            add(form);
            form.add(group);

            ListView<Flight> persons = new ListView<Flight>("persons",fi) {

                @Override
                protected void populateItem(ListItem<Flight> item) {
                    item.add(new Radio<Flight>("radio", item.getModel()));
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
