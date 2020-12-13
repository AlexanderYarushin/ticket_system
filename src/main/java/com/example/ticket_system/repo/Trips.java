package com.example.ticket_system.repo;

import com.example.ticket_system.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Trips extends CrudRepository<Trip, Long> {
  List<Trip> findByStartdateAndFrompointAndTopoint(String date, Integer startPoint, Integer toPoint);
  Trip findById(Integer id);
}
