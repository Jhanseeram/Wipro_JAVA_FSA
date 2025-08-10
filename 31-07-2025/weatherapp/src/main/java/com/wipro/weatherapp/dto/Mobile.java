package com.wipro.weatherapp.dto;

public class Mobile {
    Integer id;
    String make;
    String modelNumber;
    double price;

    public Mobile() {}

    public Mobile(Integer id, String make, String modelNumber, double price) {
        this.id = id;
        this.make = make;
        this.modelNumber = modelNumber;
        this.price = price;
    }

    public Integer getId() { 
    	return id; 
    }
    public void setId(Integer id) { 
    	this.id = id; 
    	}

    public String getMake() { 
    	return make; 
    	}
    public void setMake(String make) { 
    	this.make = make; 
    	}

    public String getModelNumber() { 
    	return modelNumber; 
    	}
    public void setModelNumber(String modelNumber) { 
    	this.modelNumber = modelNumber; 
    	}

    public double getPrice() { 
    	return price; 
    	}
    public void setPrice(double price) { 
    	this.price = price; 
    	}
}
