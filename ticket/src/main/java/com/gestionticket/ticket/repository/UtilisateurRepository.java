package com.gestionticket.ticket.repository;

import com.gestionticket.ticket.model.Role;
import com.gestionticket.ticket.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    Optional<Utilisateur> findByEmail(String email);
    List<Utilisateur> findAllByRole(Role role);
}
