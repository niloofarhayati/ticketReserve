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

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


public class AddFlightPage extends WebPage implements Serializable {
    private TextField flightName;
    private NumberTextField flightCapacity;
    private String orgin ;
    private String destination;
    private String airpo;
    private String airli;
    private FlightGateway flightGateway ;
    private CityGateway cityGateway;
    private AirlineGateway airlineGateway;
    private AirportGateway airportGateway ;
    private  List<String> airLineList;
    private List<String> airPortList;
    private static List<String> cities;

    public AddFlightPage() {
        airLineList=new LinkedList<String>();
        airPortList=new LinkedList<String>();
        cities=new LinkedList<String>();
        airportGateway =new AirportGateway();
        airlineGateway =new AirlineGateway();
        cityGateway = new CityGateway();
        flightGateway = new FlightGateway();
        orgin = "tehran";
        destination = "tehran";
    }
    @Override
    protected void onInitialize() {
        super.onInitialize();
//        Session session = getSession();
//        Boolean login = (Boolean) session.getAttribute("login");
        ExtendedSession extendedSession=ExtendedSession.get();
        Boolean login=extendedSession.getLogined();
        if (login) {
            add(new AdminPanelPage("AdminPanel"));

            flightCapacity = new NumberTextField("flightCapacity", new Model(0));
            flightName = new TextField("flightName", new Model(""));
            Label nameLabel;
            add(nameLabel = new Label("name_label", new Model("نام پرواز")));
            Label capacityLabel;
            add(capacityLabel = new Label("capacity_label", new Model("ظرفیت")));
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


            Form<?> form = new Form<Void>("form") {
                @Override
                protected void onSubmit() {
                    try {
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
                catch (Exception e){
                    e.printStackTrace();
                    info("انجام درخواست شما در حال حاضر امکان پذیر نمی باشد");
                }

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
            form.add(nameLabel);
            form.add(flightCapacity);
            form.add(capacityLabel);
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

    public void setAirpo(String airpo) {
        this.airpo = airpo;
    }

    public void setAirli(String airli) {
        this.airli = airli;
    }
}



