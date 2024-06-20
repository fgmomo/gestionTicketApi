package com.gestionticket.ticket.controller;

import com.gestionticket.ticket.model.Priorite;

import com.gestionticket.ticket.service.PrioriteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/priorite")
@AllArgsConstructor
public class PrioriteController {

    private final PrioriteService prioriteService;

    @PostMapping("/create")
    public Priorite create(@RequestBody Priorite priorite){
        return prioriteService.creer(priorite);
    }
    @GetMapping("/read")
    public List<Priorite> read(){

        return prioriteService.Lire();
    }
    @PutMapping("/update/{id}")
    public Priorite update(@PathVariable Long id , @RequestBody Priorite priorite){
        return prioriteService.modifier(id,priorite);
    }
    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable Long id){
        return prioriteService.supprimer(id);
    }
}
