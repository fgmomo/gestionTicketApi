package com.gestionticket.ticket.service;


import com.gestionticket.ticket.model.Priorite;

import java.util.List;

public interface PrioriteService {
    Priorite creer(Priorite priorite);
    List<Priorite> Lire();
    Priorite modifier(Long id, Priorite priorite);
    String supprimer(Long id);
}
