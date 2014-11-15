package ir.dotin.Manager;


import ir.dotin.Model.Airport;
import ir.dotin.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class AirportManager {
	  public static void main(String[] args) throws SQLException, IOException {

		  AirportManager ad=new AirportManager();

	    }

    public Airport find(String id){
       Session session = HibernateUtil.getSessionFactory().openSession();
       Transaction trans = session.beginTransaction();
            try {
            Airport b = ( Airport) session.get( Airport.class, Long.parseLong(id));
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

    public  Airport destroy(String id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        try {

        Airport b = ( Airport) session.get( Airport.class, Long.parseLong(id));
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
    public List list(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        try {
        List l = session.createQuery("from  Airport").list();
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

    public Boolean save( Airport b){
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
    public void update(String id, String name,String place,Integer capacity){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        try {
        Airport b = ( Airport) session.get( Airport.class, Long.parseLong(id));
        b.setName(name);
        b.setPlace(place);
        b.setCapacity(capacity);
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

    private Boolean createAndStoreAdmin(String place,String name,Integer capacity)  {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        try {

        Airport ad = new  Airport();
        ad.setName(name);
        ad.setPlace(place);
        ad.setCapacity(capacity);
        session.save(ad);
        session.getTransaction().commit();
            return true;
        } catch (HibernateException he) {
            he.printStackTrace();
            if (trans != null)
                trans.rollback();
            return  false;
        } finally {
            session.close();
        }
    }
}

