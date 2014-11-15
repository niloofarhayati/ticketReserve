package ir.dotin.Manager;


import ir.dotin.Model.User;
import ir.dotin.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class AdminManager {

	public boolean Login(String username, String password) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = session.beginTransaction();
		try {
			 Query query = session.createQuery(
					"from User where username=:username and password=:password");
			 	query.setParameter("username", username);
		        query.setParameter("password", password);
		        User id=(User) query.uniqueResult();
			if (id!=null&&id.getId()!= null&&id.getId()!=0) {
				session.getTransaction().commit();
				return true;
			} else
				return false;
		} catch (Exception he) {
			he.printStackTrace();
			if (trans != null)
				trans.rollback();
			return false;
		} finally {
			session.close();
		}
	}
    public User find(String id){
       Session session = HibernateUtil.getSessionFactory().openSession();
       Transaction trans = session.beginTransaction();
            try {
            User b = (User) session.get(User.class, Long.parseLong(id));
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
    public Boolean IsAdmin(String id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        try {
            User b = (User) session.get(User.class, Long.parseLong(id));
            session.getTransaction().commit();
            if(b.getType()==1)
                return  true;
            else{
                return false;
            }
        } catch (HibernateException he) {
            he.printStackTrace();
            if (trans != null)
                trans.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    public User destroy(String id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        try {

        User b = (User) session.get(User.class, Long.parseLong(id));
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
        List l = session.createQuery("from User").list();
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

    public Boolean save(User b){
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
    public void update(String id, String first,String last,String username,String pass,Integer type){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        try {
        User b = (User) session.get(User.class, Long.parseLong(id));
        b.setFirst_name(first);
        b.setLast_name(last);
        b.setType(type);
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

    private void createAndStoreAdmin(String last,String first, String pass, String username,Integer type)  {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        try {

        User ad = new User();
        ad.setLast_name(first);
        ad.setFirst_name(first);
        ad.setPassword(pass);
        ad.setUsername(username);
            ad.setType(type);
        session.save(ad);
        session.getTransaction().commit();
        } catch (HibernateException he) {
            he.printStackTrace();
            if (trans != null)
                trans.rollback();
        } finally {
            session.close();
        }
    }
}

