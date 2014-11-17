package ir.dotin.Wicket; /**
 * Created by niloofar on 11/8/14.
 */

import org.apache.wicket.protocol.http.WebApplication;

public class LoginApplication extends WebApplication {
    public LoginApplication() {
    }

    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class getHomePage() {
        return Login.class;
    }
}
