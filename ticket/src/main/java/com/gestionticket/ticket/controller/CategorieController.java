package com.gestionticket.ticket.controller;

import com.gestionticket.ticket.model.Categorie;
import com.gestionticket.ticket.service.CategorieService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/categorie")
@AllArgsConstructor
public class CategorieController {

    private final CategorieService categorieService;

    @PostMapping("/create")
    public Categorie create(@RequestBody Categorie categorie){
        return categorieService.creer(categorie);
    }
    @GetMapping("/read")
    public List<Categorie> read(){

        return categorieService.Lire();
    }
    @PutMapping("/update/{id}")
    public Categorie update(@PathVariable Long id , @RequestBody Categorie categorie){
        return categorieService.modifier(id,categorie);
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        return categorieService.supprimer(id);
    }
}
