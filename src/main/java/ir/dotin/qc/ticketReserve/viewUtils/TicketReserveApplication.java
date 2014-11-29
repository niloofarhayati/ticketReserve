package ir.dotin.qc.ticketReserve.viewUtils;
/**
 * Created by niloofar on 11/8/14.
 */

import ir.dotin.qc.ticketReserve.user.LoginPage;
import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;

public class TicketReserveApplication extends WebApplication {
    public TicketReserveApplication() {
    }

    @Override
    public Session newSession(Request request, Response response) {
        System.out.println("salam");
        return new ExtendedSession(request);
    }
    @Override
    public Class getHomePage() {
        return LoginPage.class;
    }
//    @Override
//    public void init() {
//        super.init();
//        getResourceSettings().getResourceFinders().add(new WebApplicationPath(getServletContext(), "pages"));
//    }
}
