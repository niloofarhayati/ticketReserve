package ir.dotin.qc.ticketReserve.gateway;


import ir.dotin.qc.ticketReserve.model.Flight;
import ir.dotin.qc.ticketReserve.util.HibernateUtil;
import org.hibernate.Session;

import java.util.List;


public class FlightGateway extends Gateway {

    public List<Flight> flightList(String destination, String orgin) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        CityGateway cityGateway = new CityGateway();
        try {
            Integer destinationID = cityGateway.findCity(destination);
            Integer orginID = cityGateway.findCity(orgin);
            List fl = session.createQuery(
                    "from Flight where destination_id=" + destinationID + " and orgin_id=" + orginID).list();
          //  if (fl != null && !fl.isEmpty()) {
                session.getTransaction().commit();
                return fl;
          //  }
        } finally {
            session.close();
        }
    }


    public String Reserve(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
       session.beginTransaction();
        try {
            Flight b = (Flight) session.get(Flight.class, id);
            if (b.getCapacity() >= 1) {
                b.setCapacity(b.getCapacity() - 1);
                session.update(b);
                session.getTransaction().commit();
                return "Success";
            } else {
                return "Flight is full";
            }
        } finally {
            session.close();
        }
    }
    public String UnReserve(Integer id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            Flight b = (Flight) session.get(Flight.class, id);
                b.setCapacity(b.getCapacity() + 1);
                session.update(b);
                session.getTransaction().commit();
                return "Success";
        }  finally {
            session.close();
        }
    }

    public List<Flight> list() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        try {
            List<Flight> l = session.createQuery("from  Flight ").list();
            session.getTransaction().commit();
            return l;
        }  finally {
            session.close();
        }
    }

    public Boolean update(Integer id, Integer airlinID, String name, Integer airportID,
                       Integer capacity, Integer orginID, Integer destinationID) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
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
            return true;
        } finally {
            session.close();
        }
    }

    private void createAndStoreAdmin(Integer airlinID, String name, Integer airportID,
                                     Integer capacity, Integer orginID, Integer destinationID) {
        Session session = HibernateUtil.getSessionFactory().openSession();
         session.beginTransaction();
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
        }  finally {
            session.close();
        }
    }
}

