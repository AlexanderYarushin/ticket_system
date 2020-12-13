package com.example.ticket_system.repo;

import com.example.ticket_system.model.PurchaseTrip;
import com.example.ticket_system.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Users extends CrudRepository<User, Long> {
    User findBySessionid(String sessionID);
    User findByEmailAndPassword(String email, String password);
}
