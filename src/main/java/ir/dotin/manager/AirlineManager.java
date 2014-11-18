package ir.dotin.manager;


import ir.dotin.model.Airline;
import ir.dotin.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class AirlineManager {
    public static void main(String[] args) throws SQLException, IOException {

        AirlineManager ad = new AirlineManager();

    }

    public Airline find(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        try {
            Airline b = (Airline) session.get(Airline.class, Long.parseLong(id));
            session.getTransaction().commit();
            return b;
        } catch (HibernateException he) {
            he.printStackTrace();
            if (trans != null)
                trans.rollback();
            return null;
        } finally {
            session.close();
        }
    }

    public Airline destroy(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        try {

            Airline b = (Airline) session.get(Airline.class, Long.parseLong(id));
            session.delete(b);
            session.getTransaction().commit();
            return b;
        } catch (HibernateException he) {
            he.printStackTrace();
            if (trans != null)
                trans.rollback();
            return null;
        } finally {
            session.close();
        }
    }


    @SuppressWarnings("rawtypes")
    public List list() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        try {
            List l = session.createQuery("from  Airline").list();
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

    public Boolean save(Airline b) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        try {
            session.save(b);
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

