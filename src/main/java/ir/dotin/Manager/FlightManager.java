package ir.dotin.Manager;


import ir.dotin.Model.Flight;
import ir.dotin.Model.User;
import ir.dotin.utils.HibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;


public class FlightManager {
	  public static void main(String[] args) throws SQLException, IOException {

		  FlightManager ad=new FlightManager();
	    }


	public List<Flight> flightList(String destination, String orgin) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction trans = session.beginTransaction();
        CityManager cityManager=new CityManager();
		try {
            Integer destinationID=cityManager.findCity(destination);
            Integer orginID=cityManager.findCity(orgin);
			List fl = session.createQuery(
					"from Flight where destination_id="+destinationID+"and orgin_id=:"+orginID).list();
			if (fl!=null&&!fl.isEmpty()) {
				session.getTransaction().commit();
				return fl;
			} else
				return null;
		} catch (Exception he) {
			he.printStackTrace();
			if (trans != null)
				trans.rollback();
			return null;
		} finally {
			session.close();
		}
	}
    public String Reserve(Integer id){
       Session session = HibernateUtil.getSessionFactory().openSession();
       Transaction trans = session.beginTransaction();
            try {
             Flight b = (Flight) session.get(Flight.class, id);
                if(b.getCapacity()>=1) {
                    b.setCapacity(b.getCapacity() - 1);
                    session.update(b);
                    session.getTransaction().commit();
                    return "Success";
                }
                else{
                    return  "Flight is full";
                }
        } catch (HibernateException he) {
        he.printStackTrace();
        if (trans != null)
            trans.rollback();
        return null;
    } finally {
        session.close();
    }
    }

    public Flight destroy(Integer id){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        try {

            Flight b = (Flight) session.get(Flight.class, id);
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
        List l = session.createQuery("from Flight").list();
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

    public Boolean save(Flight b){
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
    public void update(Integer id,Integer airlinID,String name, Integer airportID,
                       Integer capacity,Integer orginID,Integer destinationID){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        try {
            Flight flight = (Flight) session.get(Flight.class, id);
            flight.setAirLine_id(airlinID);
            flight.setAirport_id(airportID);
            flight.setCapacity(capacity);
            flight.setDestination_id(destinationID);
            flight.setName(name);
            flight.setOrgin_id(orginID);
        session.update(flight);
        session.getTransaction().commit();
    } catch (HibernateException he) {
        he.printStackTrace();
        if (trans != null)
            trans.rollback();
    } finally {
        session.close();
    }
    }

    private void createAndStoreAdmin(Integer airlinID,String name, Integer airportID,
                                     Integer capacity,Integer orginID,Integer destinationID)  {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction trans = session.beginTransaction();
        try {

        Flight flight = new Flight();
            flight.setAirLine_id(airlinID);
            flight.setAirport_id(airportID);
            flight.setCapacity(capacity);
            flight.setDestination_id(destinationID);
            flight.setName(name);
            flight.setOrgin_id(orginID);
        session.save(flight);
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

