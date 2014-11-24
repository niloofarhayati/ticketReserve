package ir.dotin.qc.ticketReserve.gateway;


import ir.dotin.qc.ticketReserve.model.Airline;
import ir.dotin.qc.ticketReserve.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class AirlineGateway extends Gateway {
    public static void main(String[] args) throws SQLException, IOException {

        AirlineGateway ad = new AirlineGateway();

    }

    public Integer findAirline(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        try {
            Query query = session.createQuery("from Airline where name=:name");
            query.setParameter("name", name);
            Airline airline = (Airline) query.uniqueResult();
            return airline.getId();
        } catch (HibernateException he) {
            he.printStackTrace();
            if (trans != null)
                trans.rollback();
            return null;
        } finally {
            session.close();
        }
    }
    public List<Airline> list() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        try {
            List<Airline> l = session.createQuery("from  Airline").list();
            session.getTransaction().commit();
            return l;
        } catch (HibernateException he) {
            he.printStackTrace();
            if (trans != null)
                trans.rollback();
            return null;
        } finally {
            session.close();
        }
    }

    public void update(String id, String name, String place) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        try {
            Airline b = (Airline) session.get(Airline.class, Long.parseLong(id));
            b.setName(name);
            b.setPlace(place);
            session.update(b);
            session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
            if (trans != null)
                trans.rollback();
        } finally {
            session.close();
        }
    }

    private Boolean createAndStoreAdmin(String place, String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        try {

            Airline ad = new Airline();
            ad.setName(name);
            ad.setPlace(place);
            session.save(ad);
            session.getTransaction().commit();
            return true;
        } catch (HibernateException he) {
            he.printStackTrace();
            if (trans != null)
                trans.rollback();
            return false;
        } finally {
            session.close();
        }
    }
}
