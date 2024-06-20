package com.gestionticket.ticket.service;
import com.gestionticket.ticket.model.Categorie;

import java.util.List;

public interface CategorieService {
    Categorie creer(Categorie categorie);
    List<Categorie> Lire();
    Categorie modifier(Long id, Categorie categorie);
    String supprimer(Long id);
}
