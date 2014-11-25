package ir.dotin.qc.ticketReserve.gateway;


import ir.dotin.qc.ticketReserve.model.Airport;
import ir.dotin.qc.ticketReserve.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;


public class AirportGateway extends Gateway {

    public Integer findAirport(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Query query = session.createQuery("from Airport where name=:name");
            query.setParameter("name", name);
            Airport airport = (Airport) query.uniqueResult();
            return airport.getId();
        } finally {
            session.close();
        }
    }

    public void update(String id, String name, String place, Integer capacity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Airport b = (Airport) session.get(Airport.class, Long.parseLong(id));
            b.setName(name);
            b.setPlace(place);
            b.setCapacity(capacity);
            session.update(b);
            session.getTransaction().commit();
        }  finally {
            session.close();
        }
    }
    public List<Airport> list() {
        Session session = HibernateUtil.getSessionFactory().openSession();
       session.beginTransaction();
        try {
            List<Airport> l = session.createQuery("from  Airport ").list();
            session.getTransaction().commit();
            return l;
        }  finally {
            session.close();
        }
    }


    private Boolean createAndStoreAdmin(String place, String name, Integer capacity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {

            Airport ad = new Airport();
            ad.setName(name);
            ad.setPlace(place);
            ad.setCapacity(capacity);
            session.save(ad);
            session.getTransaction().commit();
            return true;
        } finally {
            session.close();
        }
    }
}

