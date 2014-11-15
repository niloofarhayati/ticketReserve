package ir.dotin.Model;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name="ticket_user")
public class User implements Serializable {

		private static final long serialVersionUID = 1L;

		
		@Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
		@Column(name="id")
		private Integer id;

		@Column(name="first_name")
		private String first_name;

		@Column(name="last_name")
		private String last_name;
		
		@Column(name="username")
		private String username; 

		@Column(name="password" )
		private String password;

        @Column(name="type" )
        private Integer type;

		
	

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getFirst_name() {
			return first_name;
		}

		public void setFirst_name(String first_name) {
			this.first_name = first_name;
		}

		public String getLast_name() {
			return last_name;
		}

		public void setLast_name(String last_name) {
			this.last_name = last_name;
		}
		
		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		

		public User() {
		}

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
