package com.wipro.carms.dto;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Car_Registration_Detail")
public class CarRegistrationDetail {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	@Column(name="registration_number")
    private String registrationNumber;
    @Column(name="date_of_registration")
	private LocalDate dateOfRegistration;
	public CarRegistrationDetail() {
		super();
	}
	public CarRegistrationDetail(Long id, String registrationNumber, LocalDate dateOfRegistration) {
		super();
		this.id = id;
		this.registrationNumber = registrationNumber;
		this.dateOfRegistration = dateOfRegistration;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public LocalDate getDateOfRegistration() {
		return dateOfRegistration;
	}
	public void setDateOfRegistration(LocalDate dateOfRegistration) {
		this.dateOfRegistration = dateOfRegistration;
	}
	@Override
	public String toString() {
		return "CarRegistrationDetail [id=" + id + ", registrationNumber=" + registrationNumber
				+ ", dateOfRegistration=" + dateOfRegistration + "]";
	}
    
    

}
