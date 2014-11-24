package ir.dotin.qc.ticketReserve.view;


import org.apache.wicket.protocol.http.WebApplication;

public class NavomaticApplication extends WebApplication {
    public NavomaticApplication() {
    }

    public Class getHomePage() {
        return Login.class;
    }
}