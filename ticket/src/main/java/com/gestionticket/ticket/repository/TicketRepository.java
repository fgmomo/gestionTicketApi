package com.gestionticket.ticket.repository;

import com.gestionticket.ticket.model.Ticket;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket,Long> {

}
