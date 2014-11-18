package ir.dotin.model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "ticket_flight")
public class Flight implements Serializable {

    private static final long serialVersionUID = 1L;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "airport_id")
    private Integer airport_id;

    @Column(name = "airLine_id")
    private Integer airLine_id;

    @Column(name = "destination_id")
    private Integer destination_id;

    @Column(name = "orgin_id")
    private Integer orgin_id;

    @Column(name = "capacity")
    private Integer capacity;

    public Flight() {
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getDestination_id() {
        return destination_id;
    }

    public void setDestination_id(Integer destination_id) {
        this.destination_id = destination_id;
    }


    public Integer getAirLine_id() {
        return airLine_id;
    }

    public void setAirLine_id(Integer airLine_id) {
        this.airLine_id = airLine_id;
    }

    public Integer getAirport_id() {
        return airport_id;
    }

    public void setAirport_id(Integer airport_id) {
        this.airport_id = airport_id;
    }

    public Integer getOrgin_id() {
        return orgin_id;
    }

    public void setOrgin_id(Integer orgin_id) {
        this.orgin_id = orgin_id;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

}
