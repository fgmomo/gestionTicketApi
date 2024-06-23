package com.gestionticket.ticket.controller;

import com.gestionticket.ticket.model.Reponse;
import com.gestionticket.ticket.service.ReponseService;
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

    @PostMapping("/create")
    public ResponseEntity<Reponse> createReponse(@RequestBody Reponse reponse) {
        Reponse createdReponse = reponseService.creer(reponse);
        return new ResponseEntity<>(createdReponse, HttpStatus.CREATED);
    }

    @GetMapping("/read")
    public ResponseEntity<List<Reponse>> getAllReponses() {
        List<Reponse> reponses = reponseService.Lire();
        return new ResponseEntity<>(reponses, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Reponse> updateReponse(@PathVariable Long id, @RequestBody Reponse reponse) {
        Reponse updatedReponse = reponseService.modifier(id, reponse);
        return new ResponseEntity<>(updatedReponse, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteReponse(@PathVariable Long id) {
        String message = reponseService.supprimer(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
