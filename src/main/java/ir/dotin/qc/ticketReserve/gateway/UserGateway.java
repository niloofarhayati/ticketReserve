package ir.dotin.qc.ticketReserve.gateway;


import ir.dotin.qc.ticketReserve.model.User;
import ir.dotin.qc.ticketReserve.util.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;


public class UserGateway extends Gateway {

    public Integer Login(String username, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        try {
            Query query = session.createQuery(
                    "from User where username=:username and password=:password");
            query.setParameter("username", username);
            query.setParameter("password", password);
            User id = (User) query.uniqueResult();
            if (id != null && id.getId() != null && id.getId() != 0) {
                session.getTransaction().commit();
                return id.getId();
            } else
                return 0;
        }  finally {
            session.close();
        }
    }

    public Integer findID(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        try {
            Query query = session.createQuery(
                    "from User where username=:username");
            query.setParameter("username", username);
            User b = (User) query.uniqueResult();
            if(b!=null){
            session.getTransaction().commit();
            return b.getId();
            }
            else{
                return 0;
            }
        } finally {
            session.close();
        }
    }

    public Boolean IsAdmin(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            User b = (User) session.get(User.class, id);
            session.getTransaction().commit();
            if (b.getType() == 0)
                return true;
            else {
                return false;
            }
        } finally {
            session.close();
        }
    }


    public List<User> list() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<User> l = session.createQuery("from  User").list();
            session.getTransaction().commit();
            return l;
        } finally {
            session.close();
        }
    }

    public Boolean update(String id, String first, String last, String username, String pass, Integer type) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            User b = (User) session.get(User.class, Integer.parseInt(id));
            b.setFirst_name(first);
            b.setLast_name(last);
            b.setType(type);
            session.update(b);
            session.getTransaction().commit();
            return  true;
        }  finally {
            session.close();
        }
    }

    public Boolean createAndStoreAdmin(String last, String first, String pass, String username, Integer type) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {

            User ad = new User();
            ad.setLast_name(first);
            ad.setFirst_name(first);
            ad.setPassword(pass);
            ad.setUsername(username);
            ad.setType(type);
            session.save(ad);
            session.getTransaction().commit();
            return true;
        } finally {
            session.close();
        }
    }
}

