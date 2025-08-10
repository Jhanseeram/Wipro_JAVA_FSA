package com.wipro.weatherapp.controller;

import com.wipro.weatherapp.dto.Mobile;
import com.wipro.weatherapp.service.MobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MobileController {

    @Autowired
    private MobileService service;

    @PostMapping("/mobile")
    public String addMobile(@RequestBody Mobile mobile) {
        if (mobile.getId() == null) {
            return "ID is required";
        }
        service.addMobile(mobile);
        return "Mobile added successfully";
    }

    @GetMapping("/mobile")
    public List<Mobile> getAllMobiles() {
        return service.getAllMobiles();
    }

    @GetMapping("/mobile/{id}")
    public Mobile getMobileById(@PathVariable int id) {
        Mobile mobile = service.getMobileById(id);
        if (mobile == null) {
            throw new RuntimeException("Mobile with ID " + id + " not found");
        }
        return mobile;
    }

    @PutMapping("/mobile")
    public String updateMobile(@RequestBody Mobile mobile) {
        if (mobile.getId() == null) {
            return "ID is required for update";
        }
        service.updateMobile(mobile);
        return "Mobile updated successfully";
    }

    @DeleteMapping("/mobile/{id}")
    public String deleteMobile(@PathVariable int id) {
        service.deleteMobile(id);
        return "Mobile deleted successfully";
    }
}
