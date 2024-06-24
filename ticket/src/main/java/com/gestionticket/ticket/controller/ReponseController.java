package com.gestionticket.ticket.controller;

import com.gestionticket.ticket.model.Reponse;
import com.gestionticket.ticket.service.ReponseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reponses")
@AllArgsConstructor
public class ReponseController {

    private final ReponseService reponseService;

    @Operation(summary = "Créer une nouvelle réponse", description = "Ajoute une nouvelle réponse au système.")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Réponse créée avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
    @PostMapping("/create")
    public ResponseEntity<Reponse> createReponse(@RequestBody Reponse reponse) {
        Reponse createdReponse = reponseService.creer(reponse);
        return new ResponseEntity<>(createdReponse, HttpStatus.CREATED);
    }
    @Operation(summary = "Lire toutes les réponses", description = "Récupère une liste de toutes les réponses.")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Liste des réponses récupérée avec succès")
    })

    @GetMapping("/read")
    public ResponseEntity<List<Reponse>> getAllReponses() {
        List<Reponse> reponses = reponseService.Lire();
        return new ResponseEntity<>(reponses, HttpStatus.OK);
    }
    @Operation(summary = "Mettre à jour une réponse", description = "Met à jour une réponse existante.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reponse mis à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Reponse non trouvé")
    })
    @PutMapping("/update/{id}")
    public ResponseEntity<Reponse> updateReponse(@PathVariable Long id, @RequestBody Reponse reponse) {
        Reponse updatedReponse = reponseService.modifier(id, reponse);
        return new ResponseEntity<>(updatedReponse, HttpStatus.OK);
    }
    /**
    @Operation(summary = "Supprimer une reponse", description = "Supprime une reponse par ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Reponse supprimée avec succès"),
            @ApiResponse(responseCode = "404", description = "Reponse non trouvée")
    })

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReponse(@PathVariable Long id) {
        String message = reponseService.supprimer(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
    **/
}
