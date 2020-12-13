package com.example.ticket_system.controllers;

import com.example.ticket_system.model.City;
import com.example.ticket_system.model.SupportDays;
import com.example.ticket_system.model.Trip;
import com.example.ticket_system.repo.Cities;
import com.example.ticket_system.repo.Trips;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
public class Search {
    @Autowired
    private Cities cities;

    @Autowired
    private Trips trips;

    String[] day_to_string = {"Воскресенье", "Понедельник", "Вторник", "Среда", "Четверг", "Пятница", "Суббота"};


    @GetMapping("/search")
    public String Search(@RequestParam String from,
                         @RequestParam String to,
                         @RequestParam String date,
                         @RequestParam Integer old,
                         @RequestParam Integer young,
                         Map<String, Object> model) throws ParseException {

        if (from.isEmpty() || to.isEmpty() || date.isEmpty() || old < 1 || old > 5 || young < 0 || young > 5)
            return "redirect:/error";

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date _date = dateFormat.parse(date);


        Calendar calendar = Calendar.getInstance();
        calendar.setTime(_date);


        List<SupportDays> days_list = new ArrayList<>();

        calendar.add(Calendar.DATE, -3);
        for (int index = 0; index < 5; index++) {
            calendar.add(Calendar.DATE, 1);
            days_list.add(new SupportDays(dateFormat.format(calendar.getTime()),
                    day_to_string[calendar.get(Calendar.DAY_OF_WEEK) - 1]));
        }


        City find_from = cities.findByName(from);
        City find_to = cities.findByName(to);

        List<Trip> trips = this.trips.findByStartdateAndFrompointAndTopoint(date, find_from.getId(), find_to.getId());


        if (trips.isEmpty()) {
            model.put("not_found", "Рейсы не найдены, выберите другую дату");
        } else {
            model.put("not_found", "");
            model.put("trips", trips);
        }

        model.put("from", from);
        model.put("to", to);
        model.put("from_region", find_from.getRegion());
        model.put("to_region", find_to.getRegion());
        model.put("date", date);

        model.put("dates", days_list);
        model.put("old", old);
        model.put("young", young);


        return "search_page";
    }


}