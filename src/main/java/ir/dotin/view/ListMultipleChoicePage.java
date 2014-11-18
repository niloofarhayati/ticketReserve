package ir.dotin.view;

/**
 * Created by niloofar on 11/17/14.
 */

import ir.dotin.manager.AdminManager;
import ir.dotin.model.User;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.ListMultipleChoice;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;

import java.util.ArrayList;
import java.util.List;

//import org.apache.wicket.pag

public class ListMultipleChoicePage extends WebPage {

    static AdminManager user = new AdminManager();
    private static final List<User> NUMBERS = user.list();

    // Number 6 is selected by default
    private ArrayList<User> select = new ArrayList<User>(
            //Arrays.asList(new String[] { "Number 6" }));
            user.list());

    public ListMultipleChoicePage() {

        add(new FeedbackPanel("feedback"));

        ListMultipleChoice<User> listNumbers = new ListMultipleChoice<User>(
                "number", new Model(select), NUMBERS);

        // listNumbers.setMaxRows(5);

        Form<?> form = new Form<Void>("form") {
            @Override
            protected void onSubmit() {

                info("Selected Number : " + select);

            }
        };

        add(form);
        form.add(listNumbers);

    }
}