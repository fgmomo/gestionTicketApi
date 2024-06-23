package com.gestionticket.ticket.service;

import com.gestionticket.ticket.model.Etat;


import java.util.List;

public interface EtatService {
    Etat creer(Etat etat);
    List<Etat> Lire();
    Etat modifier(Long id, Etat etat);
    String supprimer(Long id);

    Etat getEtatOuvert();

    Etat getEtatResolu();

    Etat getEtatEnCours();
}
