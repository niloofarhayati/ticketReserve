package ir.dotin.qc.ticketReserve.gateway;


import ir.dotin.qc.ticketReserve.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class Gateway<T> {

    public T find(String id,Class<?> cls) throws Exception{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        try {
            T b = (T) session.get(cls, Integer.parseInt(id));
            session.getTransaction().commit();
            return b;
        } finally {
            session.close();
        }
    }
//    public List list(Class<?> cls) {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction trans = session.beginTransaction();
//        try {
//            Query query=session.createQuery("from  cls");
//            query.setParameter("cls", T);
//            List l = query.list();
//            session.getTransaction().commit();
//            return l;
//        } catch (HibernateException he) {
//            he.printStackTrace();
//            if (trans != null)
//                trans.rollback();
//            return null;
//        } finally {
//            session.close();
//        }
//    }


    public Boolean destroy(String id,Class<?> cls) throws Exception{
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        try {

            T b = (T) session.get(cls, Integer.parseInt(id));
            session.delete(b);
            session.getTransaction().commit();
            return true;
        } finally {
            session.close();
        }
    }


    public ir.dotin.qc.ticketReserve.model.Transaction createConnection()throws Exception{
        ir.dotin.qc.ticketReserve.model.Transaction transaction=new ir.dotin.qc.ticketReserve.model.Transaction();
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        transaction.setSession(session);
        transaction.setTransaction(trans);
        return  transaction;
    }

    public Boolean save(T b) throws Exception{
        ir.dotin.qc.ticketReserve.model.Transaction transaction = this.createConnection();
        try {
            Session session=transaction.getSession();
            transaction.getSession().save(b);
            transaction.getSession().getTransaction().commit();
            return true;
        }  finally {
            transaction.getSession().close();
        }
    }

}

