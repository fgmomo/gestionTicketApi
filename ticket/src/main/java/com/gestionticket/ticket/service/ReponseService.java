package com.gestionticket.ticket.service;

import com.gestionticket.ticket.model.Categorie;
import com.gestionticket.ticket.model.Reponse;

import java.util.List;

public interface ReponseService {
    Reponse creer(Reponse reponse);
    List<Reponse> Lire();
    Reponse modifier(Long id, Reponse reponse);
    String supprimer(Long id);
}