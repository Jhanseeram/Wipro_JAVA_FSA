package com.example.wiprospringboot.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.wiprospringboot.repo.CitiRepo;
import com.example.wiprospringboot.service.CitiService;

@Service
public class CitiServiceImpl implements CitiService {

    @Autowired
    private CitiRepo citiRepo;

    @Override
    public List<String> getCitiList() {
        return citiRepo.getCitiList();
    }
}
