package com.gestionticket.ticket.controller;
import com.gestionticket.ticket.model.Ticket;
import com.gestionticket.ticket.model.Utilisateur;
import com.gestionticket.ticket.service.TicketService;
import com.gestionticket.ticket.service.UtilisateurService;
import lombok.AllArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/ticket")
@AllArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @PostMapping("/create")
    public Ticket create(@RequestBody Ticket ticket, @RequestParam Long utilisateurId){
        return ticketService.creer(ticket,utilisateurId);
    }
    @GetMapping("/read")
    public List<Ticket> read(){
        return ticketService.Lire();
    }
    @PutMapping("/update/{id}")
    public Ticket update(@PathVariable Long id , @RequestBody Ticket ticket){
        return ticketService.modifier(id, ticket);
    }
    @DeleteMapping("/delete")
    public String delete(@PathVariable Long id){
        return ticketService.supprimer(id);
    }
}
