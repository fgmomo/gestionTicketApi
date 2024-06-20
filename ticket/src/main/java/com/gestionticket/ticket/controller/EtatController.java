package com.gestionticket.ticket.controller;


import com.gestionticket.ticket.model.Etat;
import com.gestionticket.ticket.service.EtatService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/etat")
@AllArgsConstructor
public class EtatController {

    private final EtatService etatService;

    @PostMapping("/create")
    public Etat create(@RequestBody Etat etat){
        return etatService.creer(etat);
    }
    @GetMapping("/read")
    public List<Etat> read(){
        return etatService.Lire();
    }
    @PutMapping("/update/{id}")
    public Etat update(@PathVariable Long id , @RequestBody Etat etat){
        return etatService.modifier(id,etat);
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        return etatService.supprimer(id);
    }
}
