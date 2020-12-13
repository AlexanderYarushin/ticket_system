package com.example.ticket_system.repo;

import com.example.ticket_system.model.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface Cities extends CrudRepository<City, Long> {
    List<City> findByNameIsStartingWith(String cityNameStart);
    City findByName(String cityName);
    City findById(Integer id);

}
