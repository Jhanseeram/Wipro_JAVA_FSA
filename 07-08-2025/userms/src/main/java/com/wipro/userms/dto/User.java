package com.wipro.userms.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="user")
public class User {
	
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	    @Column(name="user_name")
	 	private String username;
	    @Column(name="password")
	    private String password;
	    @Column(name="address")
	    private String address;
	    
	    public User()
	    {
	    	super();
	    }

		public User(int id, String username, String password, String address) {
			super();
			this.id = id;
			this.username = username;
			this.password = password;
			this.address = address;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
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

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		@Override
		public String toString() {
			return "User [id=" + id + ", username=" + username + ", password=" + password + ", address=" + address
					+ "]";
		}
	    

}
