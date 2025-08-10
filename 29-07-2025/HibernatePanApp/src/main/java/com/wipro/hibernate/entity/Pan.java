package com.wipro.hibernate.entity;

//import jakarta.persistence.Entity;
//import jakarta.persistence.Id;
//import jakarta.persistence.Table;
import jakarta.persistence.*;

@Entity
@Table(name = "pan")
public class Pan {
    @Id
    private int id;
    private String panholderName;
    private String panNumber;

    // Getters and setters
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getPanholderName() {
        return panholderName;
    }
    public void setPanholderName(String panholderName) {
        this.panholderName = panholderName;
    }
    public String getPanNumber() {
        return panNumber;
    }
    public void setPanNumber(String panNumber) {
        this.panNumber = panNumber;
    }
}

