package ir.dotin.Model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="ticket_airport")
public class Airport implements Serializable {

		private static final long serialVersionUID = 1L;


		@Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="id")
		private Integer id;

		@Column(name="name")
		private String name;

		@Column(name="place")
		private String place;

         @Column(name="capacity")
         private Integer capacity;

    public Airport() {
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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }
}
