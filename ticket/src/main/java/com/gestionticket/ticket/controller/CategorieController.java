package com.gestionticket.ticket.controller;

import com.gestionticket.ticket.model.Categorie;
import com.gestionticket.ticket.service.CategorieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/categorie")
@AllArgsConstructor
public class CategorieController {

    private final CategorieService categorieService;

    @Operation(summary = "Créer une nouvelle catégorie", description = "Ajoute une nouvelle catégorie au système.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Catégorie créée avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })


    @PostMapping("/create")
    public Categorie create(@RequestBody Categorie categorie){
        return categorieService.creer(categorie);
    }


    @Operation(summary = "Lire toutes les catégories", description = "Récupère une liste de toutes les catégories.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des catégories récupérée avec succès")
    })

    @GetMapping("/read")
    public List<Categorie> read(){

        return categorieService.Lire();
    }
    @Operation(summary = "Mettre à jour une catégorie", description = "Met à jour les informations d'une catégorie existante.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Catégorie mise à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Catégorie non trouvée")
    })
    @PutMapping("/update/{id}")
    public Categorie update(@PathVariable Long id , @RequestBody Categorie categorie){
        return categorieService.modifier(id,categorie);
    }

    @Operation(summary = "Supprimer une catégorie", description = "Supprime une catégorie par ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Catégorie supprimée avec succès"),
            @ApiResponse(responseCode = "404", description = "Catégorie non trouvée")
    })

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        return categorieService.supprimer(id);
    }
}
