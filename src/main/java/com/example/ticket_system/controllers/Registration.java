package com.example.ticket_system.controllers;

import com.example.ticket_system.model.City;
import com.example.ticket_system.model.PurchaseTrip;
import com.example.ticket_system.model.Trip;
import com.example.ticket_system.repo.Cities;
import com.example.ticket_system.repo.Purchase;
import com.example.ticket_system.repo.Trips;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class Registration {
    @Autowired
    private Trips trips;

    @Autowired
    private Cities cities;


    @Autowired
    private Purchase purchaseTrip;

    @GetMapping("/registration")
    public String Main(@RequestParam Integer travelid,@RequestParam Integer old,@RequestParam Integer young,Map<String, Object> model){
        Trip trip = trips.findById(travelid);
        City _from = cities.findById(trip.getFrompoint());
        City _to = cities.findById(trip.getTopoint());

        List<Integer> old_count = new ArrayList<>();
        for(int i = 1; i < old+1; ++i) old_count.add(i);

        List<Integer> young_count = new ArrayList<>();
        for(int i = 1; i < young+1; ++i) young_count.add(i);


        model.put("from",_from.getName());
        model.put("to",_to.getName());
        model.put("date",trip.getStartdate()+" "+trip.getStart_time());
        model.put("price",old*trip.getPrice()+(young*trip.getPrice()/2));
        model.put("old_inputs",old_count);
        model.put("young_inputs",young_count);
        model.put("old_count",old);
        model.put("young_count",young);
        model.put("travelid",travelid);

        return "registration";
    }


    @PostMapping("/registration")
    @ResponseBody
    public String Register(@RequestParam(required=false,name="data") List<String> data){
        System.out.println(data.size());
        if(data.isEmpty()) return "error";

        for(int i = 0; i < data.size(); i+=4){
            PurchaseTrip tmp = new PurchaseTrip();
            tmp.setTripsid(Integer.valueOf(data.get(i)));
            tmp.setName(data.get(i+1));
            tmp.setSurname(data.get(i+2));
            tmp.setTelephone(data.get(i+3));
            purchaseTrip.save(tmp);
        }
        return "success";
    }



}