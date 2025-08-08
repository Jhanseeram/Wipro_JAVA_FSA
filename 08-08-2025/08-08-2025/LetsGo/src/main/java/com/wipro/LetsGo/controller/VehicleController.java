package com.wipro.LetsGo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.wipro.LetsGo.entity.VehicleEntity;
import com.wipro.LetsGo.repo.*;

@RestController
@RequestMapping("/move")
public class VehicleController {

    @Autowired
    private VehicleRepo repository;
    

    @PostMapping
    public VehicleEntity saveMovement(@RequestBody VehicleEntity movement) {
        return repository.save(movement);
    }
    @GetMapping
    public List<VehicleEntity> getAllVehicles() {
        return repository.findAll();
    }
}
