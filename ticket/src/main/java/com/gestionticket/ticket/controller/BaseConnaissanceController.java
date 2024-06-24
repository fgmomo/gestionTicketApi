package com.gestionticket.ticket.controller;

import com.gestionticket.ticket.model.BaseConnaissance;
import com.gestionticket.ticket.service.BaseConnaissanceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/baseconnaissance")
public class BaseConnaissanceController {

    private final BaseConnaissanceService baseConnaissanceService;

    @Operation(summary = "Créer une nouvelle base de connaissance", description = "Ajoute une nouvelle base de connaissance au système.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Base de connaissance créée avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    @PostMapping("/create")
    public BaseConnaissance creer(@RequestBody BaseConnaissance baseConnaissance) {
        return baseConnaissanceService.creer(baseConnaissance);
    }
    @Operation(summary = "Lire toutes les bases de connaissance", description = "Récupère une liste de toutes les bases de connaissance.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des bases de connaissance récupérée avec succès")
    })
    @GetMapping("/read")
    public List<BaseConnaissance> lire() {
        return baseConnaissanceService.lire();
    }
    @Operation(summary = "Mettre à jour une base de connaissance", description = "Met à jour les informations d'une base de connaissance existante.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Base de connaissance mise à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Base de connaissance non trouvée")
    })
    @PutMapping("/update/{id}")
    public BaseConnaissance modifier(@PathVariable Long id, @RequestBody BaseConnaissance baseConnaissance) {
        return baseConnaissanceService.modifier(id, baseConnaissance);
    }
    @Operation(summary = "Supprimer une Base de connaissance", description = "Supprime une Base de connaissance par ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Base de connaissance supprimée avec succès"),
            @ApiResponse(responseCode = "404", description = "Ticket non trouvé")
    })
    @DeleteMapping("/delete/{id}")
    public String supprimer(@PathVariable Long id) {
        return baseConnaissanceService.supprimer(id);
    }
}
