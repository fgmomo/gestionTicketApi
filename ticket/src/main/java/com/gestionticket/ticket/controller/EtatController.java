package com.gestionticket.ticket.controller;


import com.gestionticket.ticket.model.Etat;
import com.gestionticket.ticket.service.EtatService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/etat")
@AllArgsConstructor
public class EtatController {

    private final EtatService etatService;
    @Operation(summary = "Créer un nouveau Etat", description = "Ajoute un nouveau Etat au système.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Etat créée avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })


    @PostMapping("/create")
    public Etat create(@RequestBody Etat etat){
        return etatService.creer(etat);
    }
    @Operation(summary = "Lire tous les états", description = "Récupère une liste de tous les états.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des états récupérée avec succès")
    })
    @GetMapping("/read")
    public List<Etat> read(){
        return etatService.Lire();
    }
    @Operation(summary = "Mettre à jour un état", description = "Met à jour les informations d'un état existant.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "État mis à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "État non trouvé")
    })

    @PutMapping("/update/{id}")
    public Etat update(@PathVariable Long id , @RequestBody Etat etat){
        return etatService.modifier(id,etat);
    }
    @Operation(summary = "Supprimer un état", description = "Supprime un état par ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "État supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "État non trouvé")
    })
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        return etatService.supprimer(id);
    }
}
