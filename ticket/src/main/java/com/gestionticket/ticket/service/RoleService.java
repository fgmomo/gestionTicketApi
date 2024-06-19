package com.gestionticket.ticket.service;

import com.gestionticket.ticket.model.Role;


import java.util.List;

public interface RoleService {
    Role creer(Role role);
    List<Role> Lire();
    Role modifier(Long id, Role role);
    String supprimer(Long id);
}
