package ir.dotin.qc.ticketReserve.viewUtils;

/**
 * Created by niloofar on 11/8/14.
 */

import ir.dotin.qc.ticketReserve.gateway.CityGateway;
import ir.dotin.qc.ticketReserve.model.City;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.DropDownChoice;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;


public class SearchFlightPage extends WebPage implements Serializable {
    CityGateway cityGateway;
    private static List<String> cities;
    private String selectedOrgin ;
    private String selectedDestination;

    public SearchFlightPage() {
        selectedOrgin = "tehran";
        selectedDestination = "tehran";
        cities=new LinkedList<String>();
        cityGateway = new CityGateway();
    }
    @Override
    protected void onInitialize() {
        super.onInitialize();
//        Session session = getSession();
//        Boolean login = (Boolean) session.getAttribute("login");
        ExtendedSession extendedSession=ExtendedSession.get();
        Boolean login=extendedSession.getLogined();
        if (login) {
            add(new UserPanelPage("userPanel"));
            try {
                for (City city : cityGateway.list()) {
                    cities.add(city.getName());
                }
            } catch (Exception e){
                e.printStackTrace();
                info("انجام درخواست شما در حال حاضر امکان پذیر نمی باشد");
            }
            add(new FeedbackPanel("feedback1"));

            Form<?> searchFlightForm = new Form<Void>("searchFlightForm") {
                @Override
                protected void onSubmit() {

                    PageParameters params = new PageParameters();
                    params.add("orgin", selectedOrgin);
                    params.add("destination", selectedDestination);
                    setResponsePage(ReserveFlightPage.class, params);

                }
            };
            add(searchFlightForm);

            final DropDownChoice<String> cityOrgin = new DropDownChoice<String>("cityOrgin",
                    new PropertyModel<String>(this, "selectedOrgin"), cities);
            final DropDownChoice<String> cityDestination = new DropDownChoice<String>("cityDestination",
                    new PropertyModel<String>(this, "selectedDestination"), cities);
            searchFlightForm.add(cityDestination);
            searchFlightForm.add(cityOrgin);

            cityOrgin.add(new AjaxFormComponentUpdatingBehavior("onchange") {
                @Override
                protected void onUpdate(AjaxRequestTarget target) {
                }
            });
            cityDestination.add(new AjaxFormComponentUpdatingBehavior("onchange") {
                @Override
                protected void onUpdate(AjaxRequestTarget target) {
                }
            });
        }
    }
}


