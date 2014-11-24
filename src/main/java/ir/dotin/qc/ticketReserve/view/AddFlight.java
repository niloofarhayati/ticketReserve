package ir.dotin.qc.ticketReserve.view;

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
import ir.dotin.qc.ticketReserve.model.Flight;
import org.apache.wicket.Session;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.*;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.markup.html.form.TextField;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


public class AddFlight extends WebPage implements Serializable {
    private TextField flightName;
    private NumberTextField flightCapacity;
    private Label orginLabel;
    private Label destLabel;
    private String orgin = "tehran";
    private String destination = "tehran";
    private String airpo;
    private String airli;
    private FlightGateway flightGateway = new FlightGateway();
    private CityGateway cityGateway = new CityGateway();
    private AirlineGateway airlineGateway =new AirlineGateway();
    private AirportGateway airportGateway =new AirportGateway();
    private List<String> list=new LinkedList<String>();
    private  List<String> airLineList=new LinkedList<String>();
    private List<String> airPortList=new LinkedList<String>();
    private static List<String> cities=new LinkedList<String>();

    public AddFlight() {
        Session session = getSession();
        Boolean login = (Boolean) session.getAttribute("login");
        if (login) {
            add(new AdminPanel("navomaticBorder1"));

            flightCapacity = new NumberTextField("flightCapacity", new Model(0));
            flightName = new TextField("flightName", new Model(""));
            add(orginLabel = new Label("orginLabel", new Model("نام پرواز")));
            add(destLabel = new Label("destLabel", new Model("ظرفیت")));
            for (City city : cityGateway.list()) {
                System.out.println(city.getName());
                list.add(city.getName());
            }
            for (Airline airline : airlineGateway.list()) {
                airLineList.add(airline.getName());
            }
            for (Airport airport : airportGateway.list()) {
                airPortList.add(airport.getName());
            }
            cities = list;


            Form<?> form = new Form<Void>("form") {
                @Override
                protected void onSubmit() {
                    Flight flight = new Flight();
                    flight.setName((String) flightName.getModelObject());
                    flight.setCapacity((Integer) flightCapacity.getModelObject());
                    flight.setOrgin_id(cityGateway.findCity(orgin));
                    flight.setDestination_id(cityGateway.findCity(destination));
                    flight.setAirport_id(airportGateway.findAirport(airpo));
                    flight.setAirLine_id(airlineGateway.findAirline(airli));
                    Boolean saved = flightGateway.save(flight);
                    flightCapacity.setModelObject(0);
                    flightName.setModelObject("");
                    if (saved) {
                        info("پرواز با موفقیت اضافه شد");
                    } else
                        info("انجام درخواست شما در حال حاضر امکان پذیر نمی باشد");
                }
            };
            add(form);

            final DropDownChoice<String> org = new DropDownChoice<String>("org",
                    new PropertyModel<String>(this, "orgin"), cities);
            final DropDownChoice<String> dest = new DropDownChoice<String>("dest",
                    new PropertyModel<String>(this, "destination"), cities);
            final DropDownChoice<String> airport = new DropDownChoice<String>("airport",
                    new PropertyModel<String>(this, "airpo"), airPortList);
            final DropDownChoice<String> airline = new DropDownChoice<String>("airline",
                    new PropertyModel<String>(this, "airli"), airLineList);
            form.add(org);
            form.add(dest);
            form.add(airline);
            form.add(airport);
            form.add(orginLabel);
            form.add(flightCapacity);
            form.add(destLabel);
            form.add(flightName);

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



