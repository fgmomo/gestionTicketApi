package com.gestionticket.ticket.controller;

import com.gestionticket.ticket.model.Role;
import com.gestionticket.ticket.service.RoleService;
import com.gestionticket.ticket.service.UtilisateurService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path = "/role")
@AllArgsConstructor
public class RoleController {


        private final RoleService roleService;
    @Operation(summary = "Créer un nouveau rôle", description = "Ajoute un nouveau rôle au système.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rôle créé avec succès"),
            @ApiResponse(responseCode = "400", description = "Requête invalide")
    })
        @PostMapping("/create")
        public Role create(@RequestBody Role role){
            return roleService.creer(role);
        }
    @Operation(summary = "Lire tous les rôles", description = "Récupère une liste de tous les rôles.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des rôles récupérée avec succès")
    })
        @GetMapping("/read")
        public List<Role> read(){
            return roleService.Lire();
        }
    @Operation(summary = "Mettre à jour un rôle", description = "Met à jour les informations d'un rôle existant.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rôle mis à jour avec succès"),
            @ApiResponse(responseCode = "404", description = "Rôle non trouvé")
    })
        @PutMapping("/update/{id}")
        public Role update(@PathVariable Long id , @RequestBody Role role){
            return roleService.modifier(id,role);
        }
    @Operation(summary = "Supprimer un rôle", description = "Supprime un rôle par ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Rôle supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Rôle non trouvé")
    })
        @DeleteMapping("/delete/{id}")
        public String delete(@PathVariable Long id){
            return roleService.supprimer(id);
        }
    }

