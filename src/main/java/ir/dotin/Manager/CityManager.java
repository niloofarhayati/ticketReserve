package ir.dotin.Manager;


import ir.dotin.Model.City;
import ir.dotin.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class CityManager {
	  public static void main(String[] args) throws SQLException, IOException {

		  CityManager ad=new CityManager();

	    }

    public City find(String id){
       Session session = HibernateUtil.getSessionFactory().openSession();
       Transaction trans = session.beginTransaction();
         try {
            City b = (City) session.get( City.class, Long.parseLong(id));
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

    public City destroy(String id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        try {

        City b = (City) session.get( City.class, Long.parseLong(id));
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
        List l = session.createQuery("from  City").list();
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
    public Integer findCity(String name){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        try {
           City city = (City) session.createQuery("from  City where name="+name).uniqueResult();
            session.getTransaction().commit();
            return city.getId();
        } catch (HibernateException he) {
            he.printStackTrace();
            if (trans != null)
                trans.rollback();
            return null;
        } finally {
            session.close();
        }
    }

    public Boolean save( City b){
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
    public void update(String id, String name){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        try {
        City b = (City) session.get( City.class, Long.parseLong(id));
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

    private Boolean createAndStoreAdmin(String name)  {
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
            return  false;
        } finally {
            session.close();
        }
    }
}

