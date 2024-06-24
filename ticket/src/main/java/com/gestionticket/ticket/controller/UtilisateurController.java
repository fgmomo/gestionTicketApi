package com.gestionticket.ticket.controller;

import com.gestionticket.ticket.model.Utilisateur;
import com.gestionticket.ticket.service.UtilisateurService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.hibernate.sql.Update;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/utilisateur")
@AllArgsConstructor
public class UtilisateurController {

    private final UtilisateurService utilisateurService;
    private final AuthenticationManager authenticationManager;

    @Operation(summary = "Créer un nouveau utilisateur", description = "Ajoute un nouveau Uilisateur au système.")
    @ApiResponse(responseCode = "200", description = "Utilisateur créé avec succès")
    @ApiResponse(responseCode = "403", description = "Vous n'avez pas les droits pour effectuer cette Opération")
    @PostMapping("/create")
    public Utilisateur create( @RequestBody Utilisateur utilisateur){

        return utilisateurService.creer(utilisateur);
    }
    @Operation(summary = "Lire tous les utilisateurs", description = "Récupère une liste de tous les utilisateurs.")
    @ApiResponse(responseCode = "200", description = "Liste des utilisateurs récupérée avec succès")
    @ApiResponse(responseCode = "403", description = "Vous n'avez pas les droits pour effectuer cette Opération")
    @GetMapping("/read")
    public List<Utilisateur> read(){

        return utilisateurService.Lire();
    }
    @Operation(summary = "Mettre à jour un utilisateur", description = "Met à jour les informations d'un utilisateur existant.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur mis à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé"),
            @ApiResponse(responseCode = "403", description = "Vous n'avez pas les droits pour effectuer cette Opération")
    })
    @PutMapping("/update/{id}")
    public Utilisateur update(@PathVariable Long id , @RequestBody Utilisateur utilisateur){
        return utilisateurService.modifier(id, utilisateur);
    }

    @Operation(summary = "Supprimer un utilisateur", description = "Supprime un utilisateur par ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Utilisateur supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Utilisateur non trouvé"),
            @ApiResponse(responseCode = "403", description = "Vous n'avez pas les droits pour effectuer cette Opération")
    })
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        return utilisateurService.supprimer(id);
    }
}
