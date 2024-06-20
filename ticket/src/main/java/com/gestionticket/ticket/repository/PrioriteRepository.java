package com.gestionticket.ticket.repository;

import com.gestionticket.ticket.model.Priorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrioriteRepository extends JpaRepository<Priorite,Long> {
    Priorite findByLibelle(String prioriteLibelle);
}
