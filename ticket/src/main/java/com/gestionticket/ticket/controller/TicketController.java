package com.gestionticket.ticket.controller;
import com.gestionticket.ticket.model.Ticket;
import com.gestionticket.ticket.model.Utilisateur;
import com.gestionticket.ticket.service.TicketService;
import com.gestionticket.ticket.service.UtilisateurService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/ticket")
@AllArgsConstructor
public class TicketController {
    private final TicketService ticketService;
    @Operation(summary = "Lire tous les tickets", description = "Récupère une liste de tous les tickets.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des tickets récupérée avec succès")
    })
    @PostMapping("/create")
    public Ticket create(@RequestBody Ticket ticket){
        return ticketService.creer(ticket);
    }
    @Operation(summary = "Lire tous les tickets", description = "Récupère une liste de tous les tickets.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des tickets récupérée avec succès")
    })
    @GetMapping("/read")
    public List<Ticket> read(){
        return ticketService.Lire();
    }
    @Operation(summary = "Mettre à jour un ticket", description = "Met à jour les informations d'un ticket existant.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ticket mis à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Ticket non trouvé")
    })
    @PutMapping("/update/{id}")
    public Ticket update(@PathVariable Long id , @RequestBody Ticket ticket){
        return ticketService.modifier(id, ticket);
    }


    @Operation(summary = "Supprimer un ticket", description = "Supprime un ticket par ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ticket supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Ticket non trouvé")
    })
    @DeleteMapping("/delete/id")
    public String delete(@PathVariable Long id){
        return ticketService.supprimer(id);
    }

    @Operation(summary = "Prendre en charge un ticket", description = "Marque un ticket comme pris en charge.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ticket pris en charge avec succès"),
            @ApiResponse(responseCode = "404", description = "Ticket non trouvé")
    })
    @PutMapping("/prendre-en-charge/{id}")
    public Ticket prendreEnCharge(@PathVariable Long id) {
        return ticketService.prendreEnCharge(id);
    }
    @Operation(summary = "Résoudre un ticket", description = "Marque un ticket comme résolu avec une réponse.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Ticket résolu avec succès"),
    @ApiResponse(responseCode = "404", description = "Ticket non trouvé")
    })
    @PutMapping("/resoudre/{id}")
    public Ticket resoudre(@PathVariable Long id, @RequestBody String reponse) {

        return ticketService.resoudre(id, reponse);
    }
}
