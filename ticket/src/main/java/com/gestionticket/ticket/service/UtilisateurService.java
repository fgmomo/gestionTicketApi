package com.gestionticket.ticket.service;

import com.gestionticket.ticket.model.Utilisateur;

import java.util.List;

public interface UtilisateurService {
    Utilisateur creer(Utilisateur utilisateur);
    List<Utilisateur> Lire();
    Utilisateur modifier(Long id, Utilisateur utilisateur);
    String supprimer(Long id);

}
