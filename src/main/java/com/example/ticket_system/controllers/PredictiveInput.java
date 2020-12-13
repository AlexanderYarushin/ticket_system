package com.example.ticket_system.controllers;

import com.example.ticket_system.model.City;
import com.example.ticket_system.repo.Cities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PredictiveInput {
    @Autowired
    private Cities cities;

    @PostMapping("/predinput")
    @ResponseBody
    public List<City>  Main(@RequestParam String text){
        System.out.println(1);
        List<City> predictive_result = cities.findByNameIsStartingWith(text);
        return predictive_result;
    }


}