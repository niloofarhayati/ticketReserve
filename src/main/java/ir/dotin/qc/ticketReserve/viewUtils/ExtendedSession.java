package ir.dotin.qc.ticketReserve.viewUtils;


 import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

public class ExtendedSession extends WebSession {

    private Integer userID;
    private Boolean logined;

    public ExtendedSession(Request request) {
        super(request);
    }

    public static ExtendedSession get() {
        return (ExtendedSession) Session.get();
    }


    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Boolean getLogined() {
        return logined;
    }

    public void setLogined(Boolean logined) {
        this.logined = logined;
    }
}
