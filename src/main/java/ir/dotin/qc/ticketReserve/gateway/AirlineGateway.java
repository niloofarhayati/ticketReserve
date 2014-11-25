package ir.dotin.qc.ticketReserve.gateway;


import ir.dotin.qc.ticketReserve.model.Airline;
import ir.dotin.qc.ticketReserve.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;


public class AirlineGateway extends Gateway {


    public Integer findAirline(String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
       try {
           Query query = session.createQuery("from Airline where name=:name");
           query.setParameter("name", name);
           Airline airline = (Airline) query.uniqueResult();
           return airline.getId();
       }
       finally {
            session.close();
        }
    }
    public List<Airline> list() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<Airline> l = session.createQuery("from  Airline").list();
            session.getTransaction().commit();
            return l;
        }  finally {
           session.close();
        }
    }

    public void update(String id, String name, String place) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Airline b = (Airline) session.get(Airline.class, Long.parseLong(id));
            b.setName(name);
            b.setPlace(place);
            session.update(b);
            session.getTransaction().commit();
        }  finally {
            session.close();
        }
    }

    private Boolean createAndStoreAdmin(String place, String name) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {

            Airline ad = new Airline();
            ad.setName(name);
            ad.setPlace(place);
            session.save(ad);
            session.getTransaction().commit();
            return true;
        }  finally {
            session.close();
        }
    }
}

