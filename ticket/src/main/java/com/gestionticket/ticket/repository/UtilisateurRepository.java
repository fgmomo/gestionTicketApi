package com.gestionticket.ticket.repository;

import com.gestionticket.ticket.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {

}
