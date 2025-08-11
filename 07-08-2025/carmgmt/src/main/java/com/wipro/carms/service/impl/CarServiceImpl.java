package com.wipro.carms.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.carms.dto.Car;
import com.wipro.carms.repo.CarRepo;
import com.wipro.carms.service.CarService;

@Service
public class CarServiceImpl implements CarService {
	
	 @Autowired
	    private CarRepo carRepository;

	    @Override
	    public Car create(Car car) {
	        return carRepository.save(car);
	    }

	    @Override
	    public Car update(Long id, Car car) {
	        car.setId(id);
	        return carRepository.save(car);
	    }

	    @Override
	    public void delete(Long id) {
	        carRepository.deleteById(id);
	    }

	    @Override
	    public Car get(Long id) {
	        return carRepository.findById(id).orElse(null);
	    }

	    @Override
	    public List<Car> getAll() {
	        return carRepository.findAll();
	    }

}
