package ir.dotin.qc.ticketReserve.gateway;


import ir.dotin.qc.ticketReserve.model.Reserve;
import ir.dotin.qc.ticketReserve.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class ReserveGateway extends Gateway {
    public void update(Integer flightID, Integer userID, Integer id) throws Exception{
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Reserve b = (Reserve) session.get(Reserve.class, id);
            b.setFlightID(flightID);
            b.setUserID(userID);
            session.update(b);
            session.getTransaction().commit();
        }  finally {
            session.close();
        }
    }
    public List<Reserve> list(Integer id) throws Exception{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        try {
            List<Reserve> l = session.createQuery("from  Reserve where userID="+id).list();
            session.getTransaction().commit();
            return l;
        } finally {
            session.close();
        }
    }


    private Boolean createAndStoreAdmin(Integer flightID, Integer userID)throws Exception {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        try {

            Reserve ad = new Reserve();
            ad.setId(userID);
            ad.setFlightID(flightID);
            session.save(ad);
            session.getTransaction().commit();
            return true;
        } finally {
            session.close();
        }
    }
}

