package com.gestionticket.ticket.controller;

import com.gestionticket.ticket.model.Priorite;

import com.gestionticket.ticket.service.PrioriteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/priorite")
@AllArgsConstructor
public class PrioriteController {

    private final PrioriteService prioriteService;
    @Operation(summary = "Créer une nouvelle réponse", description = "Ajoute une nouvelle réponse au système.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Réponse créée avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })

    @PostMapping("/create")
    public Priorite create(@RequestBody Priorite priorite){
        return prioriteService.creer(priorite);
    }
    @Operation(summary = "Lire toutes les priorités", description = "Récupère une liste de toutes les priorités du système.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des catégories récupérée avec succès")
    })
    @GetMapping("/read")
    public List<Priorite> read(){

        return prioriteService.Lire();
    }
    @Operation(summary = "Mettre à jour une priorité", description = "Met à jour les informations d'une priorité existante.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Priorité mise à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Priorité non trouvé")
    })

    @PutMapping("/update/{id}")
    public Priorite update(@PathVariable Long id , @RequestBody Priorite priorite){
        return prioriteService.modifier(id,priorite);
    }
    @Operation(summary = "Supprimer une priorité", description = "Supprime une priorité par ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Priorité supprimée avec succès"),
            @ApiResponse(responseCode = "404", description = "Priorité non trouvée")
    })

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        return prioriteService.supprimer(id);
    }
}
