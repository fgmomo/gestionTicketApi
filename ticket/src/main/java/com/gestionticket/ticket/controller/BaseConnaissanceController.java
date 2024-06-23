package com.gestionticket.ticket.controller;

import com.gestionticket.ticket.model.BaseConnaissance;
import com.gestionticket.ticket.service.BaseConnaissanceService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/baseconnaissance")
public class BaseConnaissanceController {

    private final BaseConnaissanceService baseConnaissanceService;

    @PostMapping("/create")
    public BaseConnaissance creer(@RequestBody BaseConnaissance baseConnaissance) {
        return baseConnaissanceService.creer(baseConnaissance);
    }

    @GetMapping("/read")
    public List<BaseConnaissance> lire() {
        return baseConnaissanceService.lire();
    }

    @PutMapping("/update/{id}")
    public BaseConnaissance modifier(@PathVariable Long id, @RequestBody BaseConnaissance baseConnaissance) {
        return baseConnaissanceService.modifier(id, baseConnaissance);
    }

    @DeleteMapping("/delete/{id}")
    public String supprimer(@PathVariable Long id) {
        return baseConnaissanceService.supprimer(id);
    }
}
