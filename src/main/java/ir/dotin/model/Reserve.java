package ir.dotin.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by niloofar on 11/18/14.
 */
@Entity
@Table(name = "ticket_Reserve")
public class Reserve implements Serializable {
    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userID;

    @Column(name = "flight_id")
    private Integer flightID;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Reserve() {

    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public Integer getFlightID() {
        return flightID;
    }

    public void setFlightID(Integer flightID) {
        this.flightID = flightID;
    }
}
