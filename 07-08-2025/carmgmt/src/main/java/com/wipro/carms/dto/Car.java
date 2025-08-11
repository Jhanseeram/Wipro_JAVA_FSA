package com.wipro.carms.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="car")
public class Car {
	
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
		
		@Column(name="make")
	    private String make;

	    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	    @JoinColumn(name = "registration_detail_id", referencedColumnName = "id")
	    private CarRegistrationDetail carRegistrationDetail;

		public Car() {
			super();
		}

		public Car(Long id, String make, CarRegistrationDetail carRegistrationDetail) {
			super();
			this.id = id;
			this.make = make;
			this.carRegistrationDetail = carRegistrationDetail;
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getMake() {
			return make;
		}

		public void setMake(String make) {
			this.make = make;
		}

		public CarRegistrationDetail getCarRegistrationDetail() {
			return carRegistrationDetail;
		}

		public void setCarRegistrationDetail(CarRegistrationDetail carRegistrationDetail) {
			this.carRegistrationDetail = carRegistrationDetail;
		}

		@Override
		public String toString() {
			return "Car [id=" + id + ", make=" + make + ", carRegistrationDetail=" + carRegistrationDetail + "]";
		}
	    
	    

}
