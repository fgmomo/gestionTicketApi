package com.gestionticket.ticket.service;

import com.gestionticket.ticket.model.Ticket;
import com.gestionticket.ticket.model.Utilisateur;


import java.util.List;

public interface TicketService {
    Ticket creer(Ticket ticket);
    List<Ticket> Lire();
    Ticket modifier(Long id, Ticket ticket);
    String supprimer(Long id);

}
