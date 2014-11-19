package ir.dotin.manager;


import ir.dotin.model.City;
import ir.dotin.model.Flight;
import ir.dotin.util.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class CityManager extends BaseManager{

    public Integer findCity(String city) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        try {
            Query query = session.createQuery("from City where name=:city");
            query.setParameter("city", city);
            City cit = (City) query.uniqueResult();
            session.getTransaction().commit();
            return cit.getId();
        } catch (HibernateException he) {
            he.printStackTrace();
            if (trans != null)
                trans.rollback();
            return null;
        } finally {
            session.close();
        }
    }

    public void update(String id, String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        try {
            City b = (City) session.get(City.class, Long.parseLong(id));
            b.setName(name);
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

    public List<City> list() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        try {
            List<City> l = session.createQuery("from  City ").list();
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

    private Boolean createAndStoreAdmin(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        try {

            City ad = new City();
            ad.setName(name);
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

