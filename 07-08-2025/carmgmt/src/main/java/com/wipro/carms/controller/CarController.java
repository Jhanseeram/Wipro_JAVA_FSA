package com.wipro.carms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.carms.dto.Car;
import com.wipro.carms.service.CarService;

@RestController
@RequestMapping("/car")
public class CarController {
	@Autowired
    private CarService carService;

    @PostMapping
    public Car create(@RequestBody Car car) {
        return carService.create(car);
    }

    @PutMapping("/{id}")
    public Car update(@PathVariable Long id, @RequestBody Car car) {
        return carService.update(id, car);
    }

    @GetMapping("/{id}")
    public Car get(@PathVariable Long id) {
        return carService.get(id);
    }

    @GetMapping
    public List<Car> getAll() {
        return carService.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        carService.delete(id);
    }

}
