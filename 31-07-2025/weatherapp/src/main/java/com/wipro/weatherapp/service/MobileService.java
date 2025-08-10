package com.wipro.weatherapp.service;

import com.wipro.weatherapp.dto.Mobile;
import com.wipro.weatherapp.repository.MobileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MobileService {

    @Autowired
    private MobileRepository repository;

    public void addMobile(Mobile mobile) {
        repository.save(mobile);
    }

    public List<Mobile> getAllMobiles() {
        return repository.findAll();
    }

    public Mobile getMobileById(int id) {
        return repository.findById(id).orElse(null);
    }

    public void updateMobile(Mobile mobile) {
        repository.update(mobile);
    }

    public void deleteMobile(int id) {
        repository.deleteById(id);
    }
}
