package com.example.ticket_system.controllers;

import com.example.ticket_system.model.*;
import com.example.ticket_system.repo.Cities;
import com.example.ticket_system.repo.Purchase;
import com.example.ticket_system.repo.Trips;
import com.example.ticket_system.repo.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
public class AdminPanel {
    @Autowired
    private Cities cities;

    @Autowired
    private Trips trips;

    @Autowired
    private Users usersRepo;

    @Autowired
    private Purchase purchaseTrip;

    private User local_user;

    @PostMapping("/login")
    @ResponseBody
    public String Login(@RequestParam String email, @RequestParam String password, HttpSession session) {
        System.out.println(email);
        System.out.println(password);
        User user = usersRepo.findByEmailAndPassword(email, password);
        this.local_user = user;

        if (user == null) {
            return "error";
        }

        String sessionID = session.getId();
        user.setSessionid(sessionID);
        user.setEntered(true);
        usersRepo.save(user);


        return "success";
    }

    @PostMapping("/exit")
    @ResponseBody
    public String Exit() {


        local_user.setEntered(false);
        usersRepo.save(local_user);


        return "";
    }

    @GetMapping("/admin")
    public String Admin(HttpSession session, Map<String, Object> model) {
        List<FullInfo> fullinfo = new ArrayList();

        List<PurchaseTrip> purtrips = (List<PurchaseTrip>) purchaseTrip.findAll();


        for (int i = 0; i < purtrips.size(); ++i) {
            Trip triptmp = trips.findById(purtrips.get(i).getTripsid());
            City tmpfromcity = cities.findById(triptmp.getFrompoint());
            City tmptocity = cities.findById(triptmp.getTopoint());


            FullInfo tmp = new FullInfo();
            tmp.setName(purtrips.get(i).getName());
            tmp.setSurname(purtrips.get(i).getSurname());
            tmp.setFrom_city(tmpfromcity.getName());
            tmp.setFrom_region(tmpfromcity.getRegion());
            tmp.setTo_city(tmptocity.getName());
            tmp.setTo_region(tmptocity.getRegion());
            tmp.setStart_date(triptmp.getStartdate());
            tmp.setEnd_date(triptmp.getEnd_dateOrig());
            tmp.setStart_time(triptmp.getStart_time());
            tmp.setEnd_time(triptmp.getEnd_time());
            tmp.setTravel_time(triptmp.getTravel_time());
            tmp.setDistance(triptmp.getDistance());
            tmp.setPrice(triptmp.getPrice());
            tmp.setTelephone(purtrips.get(i).getTelephone());


            fullinfo.add(tmp);
        }


        String sessionID = session.getId();
        User user = usersRepo.findBySessionid(sessionID);

        if (user == null) {
            return "login";
        }

        if( user.getEntered() == false){
            return "login";
        }

        model.put("fullname", user.getFullname());
        model.put("fullinfo", fullinfo);
        return "admin";
    }

    final Random random = new Random();

    @PostMapping("/adminpost")
    @ResponseBody
    public String Main(@RequestParam String from, @RequestParam String to,
                       @RequestParam String date, @RequestParam String time) throws ParseException {


        City _from = cities.findByName(from);
        City _to = cities.findByName(to);

        double distance = (getDistanceFromLatLonInKm(_from.getLatitude(),
                _from.getLongitude(),
                _to.getLatitude(),
                _to.getLongitude()));
        distance += random.nextInt((int) distance / 2);

        double litresOnDistKm = (25.0 / 100.0) * distance;
        double collection = 5;
        double priceOnDistKm = (litresOnDistKm * 44.0 * collection) / 40;
        priceOnDistKm += priceOnDistKm / 100 * 13.0;

        //========================================
        SimpleDateFormat date_f = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat time_f = new SimpleDateFormat("HH:mm");
        SimpleDateFormat time_format = new SimpleDateFormat("yyyy-MM-dd-HH:mm");
        Date _time = time_format.parse(date + "-" + time);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(_time);

        double travel_time = distance / 80.0 * 60;
        travel_time += (travel_time / 2);
        System.out.println(distance);


        calendar.add(Calendar.MINUTE, (int) travel_time);

        System.out.println(date + "-" + time);
        System.out.println(time_format.format(calendar.getTime()));


        Trip result = new Trip();
        result.setFrompoint(_from.getId());
        result.setTopoint(_to.getId());
        result.setPrice((int) priceOnDistKm);
        result.setDistance((int) distance);
        result.setStart_time(time);
        result.setEnd_time(time_f.format(calendar.getTime()));
        result.setStartdate(date);
        result.setEnd_date(date_f.format(calendar.getTime()));
        result.setTravel_time(Integer.toString((int) travel_time));


        trips.save(result);

        return "";
    }

    private double getDistanceFromLatLonInKm(Float lat1, Float lon1, Float lat2, Float lon2) {
        int R = 6371;
        double dLat = deg2rad(lat2 - lat1);
        double dLon = deg2rad(lon2 - lon1);
        double a =
                Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                        Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
                                Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c;
        return d;
    }

    private double deg2rad(double deg) {
        return deg * (Math.PI / 180);

    }


}