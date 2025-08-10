package com.example.wiprospringboot.repo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.wiprospringboot.service.CitiService;

@Controller
@RequestMapping("/citi")
public class CitiController {

    @Autowired
    private CitiService citiService;

    @GetMapping("/list")
    public String getCitiList(Model model) {
        model.addAttribute("citilist", citiService.getCitiList());
        return "citilist"; // corresponds to citilist.jsp
    }
}
