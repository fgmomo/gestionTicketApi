package com.gestionticket.ticket.controller;

import com.gestionticket.ticket.model.Role;
import com.gestionticket.ticket.service.RoleService;
import com.gestionticket.ticket.service.UtilisateurService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping(path = "/role")
@AllArgsConstructor
public class RoleController {


        private final RoleService roleService;

        @PostMapping("/create")
        public Role create(@RequestBody Role role){
            return roleService.creer(role);
        }
        @GetMapping("/read")
        public List<Role> read(){
            return roleService.Lire();
        }
        @PutMapping("/update/{id}")
        public Role update(@PathVariable Long id , @RequestBody Role role){
            return roleService.modifier(id,role);
        }
        @DeleteMapping("/delete/{id}")
        public String delete(@PathVariable Long id){
            return roleService.supprimer(id);
        }
    }

