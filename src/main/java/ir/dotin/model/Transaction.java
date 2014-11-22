package ir.dotin.model;

import org.hibernate.Session;

/**
 * Created by niloofar on 11/22/14.
 */
public class Transaction {
    private Session session;
    private org.hibernate.Transaction transaction;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public org.hibernate.Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(org.hibernate.Transaction transaction) {
        this.transaction = transaction;
    }
}
