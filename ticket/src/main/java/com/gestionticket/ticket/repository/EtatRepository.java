package com.gestionticket.ticket.repository;

import com.gestionticket.ticket.model.Etat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EtatRepository extends JpaRepository<Etat,Long> {
    Optional<Etat> findByLibelle(String etatLibelle);

}