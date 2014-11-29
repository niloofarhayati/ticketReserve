package ir.dotin.qc.ticketReserve.gateway;


import ir.dotin.qc.ticketReserve.model.City;
import ir.dotin.qc.ticketReserve.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class CityGateway extends Gateway {

    public Integer findCity(String city) throws Exception{
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Query query = session.createQuery("from City where name=:city");
            query.setParameter("city", city);
            City cit = (City) query.uniqueResult();
            session.getTransaction().commit();
            return cit.getId();
        }  finally {
            session.close();
        }
    }

    public void update(String id, String name) throws Exception{
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            City b = (City) session.get(City.class, Long.parseLong(id));
            b.setName(name);
            session.update(b);
            session.getTransaction().commit();
        } finally {
            session.close();
        }
    }

    public List<City> list() throws Exception{
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<City> l = session.createQuery("from  City ").list();
            session.getTransaction().commit();
            return l;
        } finally {
            session.close();
        }
    }

    private Boolean createAndStoreAdmin(String name) throws Exception{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        try {

            City ad = new City();
            ad.setName(name);
            session.save(ad);
            session.getTransaction().commit();
            return true;
        }  finally {
            session.close();
        }
    }
}

