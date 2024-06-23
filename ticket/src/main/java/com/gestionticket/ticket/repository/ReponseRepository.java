package com.gestionticket.ticket.repository;

import com.gestionticket.ticket.model.Reponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReponseRepository extends JpaRepository<Reponse,Long> {
    Reponse findByContenu(String contenu );
}
