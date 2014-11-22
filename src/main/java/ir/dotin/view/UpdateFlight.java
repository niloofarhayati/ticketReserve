package ir.dotin.view;

/**
 * Created by niloofar on 11/19/14.
 */

import ir.dotin.manager.AirlineManager;
import ir.dotin.manager.AirportManager;
import ir.dotin.manager.CityManager;
import ir.dotin.manager.FlightManager;
import ir.dotin.model.Airline;
import ir.dotin.model.Airport;
import ir.dotin.model.City;
import ir.dotin.model.Flight;
import org.apache.wicket.Session;
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


public class UpdateFlight extends WebPage implements Serializable {
    private TextField flightName;
    private NumberTextField flightCapacity;
    private Label orginLabel;
    private Label destLabel;
    private String orgin = "tehran";
    private String destination = "tehran";
    private String airpo;
    private String airli;
    private FlightManager flightManager = new FlightManager();
    private CityManager cityManager = new CityManager();
    private AirlineManager airlineManager=new AirlineManager();
    private AirportManager airportManager=new AirportManager();
    private List<String> list=new LinkedList<String>();
    private  List<String> airLineList=new LinkedList<String>();
    private List<String> airPortList=new LinkedList<String>();
    private static List<String> cities=new LinkedList<String>();
    private Integer fly;

    public UpdateFlight(final PageParameters parameters) {
        Session session = getSession();
        Boolean login = (Boolean) session.getAttribute("login");
        if (login) {
            add(new NavomaticBorder("navomaticBorder"));
            fly = parameters.get("flight").toInt();
            flightCapacity = new NumberTextField("flightCapacity", new Model(0));
            flightName = new TextField("flightName", new Model(""));
            add(orginLabel = new Label("orginLabel", new Model("نام پرواز")));
            add(destLabel = new Label("destLabel", new Model("ظرفیت")));
            for (City city : cityManager.list()) {
                System.out.println(city.getName());
                list.add(city.getName());
            }
            for (Airline airline : airlineManager.list()) {
                airLineList.add(airline.getName());
            }
            for (Airport airport : airportManager.list()) {
                airPortList.add(airport.getName());
            }
            cities = list;


            Form<?> form = new Form<Void>("form") {
                @Override
                protected void onSubmit() {
                    String name = (String) flightName.getModelObject();
                    Integer capacity = (Integer) flightCapacity.getModelObject();
                    Integer orginID = cityManager.findCity(orgin);
                    Integer destinationID = cityManager.findCity(destination);
                    Integer airportID = airportManager.findAirport(airpo);
                    Integer airlinID = airlineManager.findAirline(airli);
                    Boolean saved = flightManager.update(fly, airlinID, name, airportID,
                            capacity, orginID, destinationID);
                    flightCapacity.setModelObject(0);
                    flightName.setModelObject("");
                    if (saved) {
                        info("پرواز با موفقیت به روز رسانی شد");
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



