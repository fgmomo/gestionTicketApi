package com.gestionticket.ticket.controller;

import com.gestionticket.ticket.model.Utilisateur;
import com.gestionticket.ticket.service.UtilisateurService;
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

    @PostMapping("/create")
    public Utilisateur create( @RequestBody Utilisateur utilisateur){

        return utilisateurService.creer(utilisateur);
    }
    @GetMapping("/read")
    public List<Utilisateur> read(){

        return utilisateurService.Lire();
    }
    @PutMapping("/update/{id}")
    public Utilisateur update(@PathVariable Long id , @RequestBody Utilisateur utilisateur){
        return utilisateurService.modifier(id, utilisateur);
    }
    @DeleteMapping("/delete")
    public String delete(@PathVariable Long id){
        return utilisateurService.supprimer(id);
    }
}
