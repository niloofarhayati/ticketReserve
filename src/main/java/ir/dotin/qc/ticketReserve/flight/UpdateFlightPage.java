package ir.dotin.qc.ticketReserve.flight;

/**
 * Created by niloofar on 11/19/14.
 */

import ir.dotin.qc.ticketReserve.gateway.AirlineGateway;
import ir.dotin.qc.ticketReserve.gateway.AirportGateway;
import ir.dotin.qc.ticketReserve.gateway.CityGateway;
import ir.dotin.qc.ticketReserve.gateway.FlightGateway;
import ir.dotin.qc.ticketReserve.model.Airline;
import ir.dotin.qc.ticketReserve.model.Airport;
import ir.dotin.qc.ticketReserve.model.City;
import ir.dotin.qc.ticketReserve.viewUtils.AdminPanelPage;
import ir.dotin.qc.ticketReserve.viewUtils.ExtendedSession;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


public class UpdateFlightPage extends WebPage implements Serializable {
    private TextField flightName;
    private NumberTextField flightCapacity;
    private String orgin ;
    private String destination;
    private String airpo;
    private String airli;
    private FlightGateway flightGateway ;
    private CityGateway cityGateway;
    private AirlineGateway airlineGateway ;
    private AirportGateway airportGateway;
    private  List<String> airLineList;
    private List<String> airPortList;
    private static List<String> cities;
    private Integer fly;
    private PageParameters parameters;

    public UpdateFlightPage(final PageParameters parameters) {
        flightGateway = new FlightGateway();
        cityGateway = new CityGateway();
        airlineGateway =new AirlineGateway();
        airportGateway =new AirportGateway();
        airLineList=new LinkedList<String>();
        airPortList=new LinkedList<String>();
        cities=new LinkedList<String>();
        orgin = "tehran";
        destination = "tehran";
        this.parameters=parameters;
    }
        @Override
        protected void onInitialize() {
            super.onInitialize();
        ExtendedSession extendedSession=ExtendedSession.get();
        Boolean login=extendedSession.getLogined();
        if (login) {
            add(new AdminPanelPage("adminPanel"));
            fly = parameters.get("flight").toInt();
            flightCapacity = new NumberTextField("flightCapacity", new Model(0));
            flightName = new TextField("flightName", new Model(""));
            Label orginLabel;
            add(orginLabel = new Label("orginLabel", new Model("نام پرواز")));
            Label destLabel;
            add(destLabel = new Label("destLabel", new Model("ظرفیت")));
            try {
                for (City city : cityGateway.list()) {
                    System.out.println(city.getName());
                    cities.add(city.getName());
                }
                for (Airline airline : airlineGateway.list()) {
                    airLineList.add(airline.getName());
                }
                for (Airport airport : airportGateway.list()) {
                    airPortList.add(airport.getName());
                }
            } catch (Exception e){
                e.printStackTrace();
            }

            Form<?> updateFlightForm = new Form<Void>("updateFlightForm") {
                @Override
                protected void onSubmit() {
                    try {
                        String name = (String) flightName.getModelObject();
                        Integer capacity = (Integer) flightCapacity.getModelObject();
                        Integer orginID = cityGateway.findCity(orgin);
                        Integer destinationID = cityGateway.findCity(destination);
                        Integer airportID = airportGateway.findAirport(airpo);
                        Integer airlinID = airlineGateway.findAirline(airli);
                        Boolean saved = flightGateway.update(fly, airlinID, name, airportID,
                                capacity, orginID, destinationID);
                        flightCapacity.setModelObject(0);
                        flightName.setModelObject("");
                        if (saved) {
                            info("پرواز با موفقیت به روز رسانی شد");
                        } else
                            info("انجام درخواست شما در حال حاضر امکان پذیر نمی باشد");
                    } catch (Exception e) {
                        e.printStackTrace();
                        info("انجام درخواست شما در حال حاضر امکان پذیر نمی باشد");
                    }
                }
            };
            add(updateFlightForm);

            final DropDownChoice<String> org = new DropDownChoice<String>("org",
                    new PropertyModel<String>(this, "orgin"), cities);
            final DropDownChoice<String> dest = new DropDownChoice<String>("dest",
                    new PropertyModel<String>(this, "destination"), cities);
            final DropDownChoice<String> airport = new DropDownChoice<String>("airport",
                    new PropertyModel<String>(this, "airpo"), airPortList);
            final DropDownChoice<String> airline = new DropDownChoice<String>("airline",
                    new PropertyModel<String>(this, "airli"), airLineList);
            updateFlightForm.add(org);
            updateFlightForm.add(orginLabel);
            updateFlightForm.add(dest);
            updateFlightForm.add(destLabel);
            updateFlightForm.add(airline);
            updateFlightForm.add(airport);
            updateFlightForm.add(flightName);
            updateFlightForm.add(flightCapacity);

            org.add(new AjaxFormComponentUpdatingBehavior("onchange") {
                @Override
                protected void onUpdate(AjaxRequestTarget target) {
                }
            });
            dest.add(new AjaxFormComponentUpdatingBehavior("onchange") {
                @Override
                protected void onUpdate(AjaxRequestTarget target) {
                }
            });
            add(new FeedbackPanel("feedback1"));
        }
    }
}



