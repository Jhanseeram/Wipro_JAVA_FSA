package com.wipro.carms.service;

import java.util.List;

import com.wipro.carms.dto.Car;

public interface CarService {
	
	Car create(Car car);
    
	Car update(Long id, Car car);
    
	void delete(Long id);
    
	Car get(Long id);
    
	List<Car> getAll();

}
