package com.example.ticket_system.repo;

import com.example.ticket_system.model.PurchaseTrip;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Purchase extends CrudRepository<PurchaseTrip, Long> {

}
