package com.gestionticket.ticket.service;
import com.gestionticket.ticket.model.Role;
import com.gestionticket.ticket.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    @Override
    public Role creer(Role role) {
        return roleRepository.save(role);
    }
    @Override
    public List<Role> Lire() {
        return roleRepository.findAll();
    }
    @Override
    public Role modifier(Long id, Role role) {
        return roleRepository.findById(id)
                .map(p->{
                   p.setLibelle(role.getLibelle());
                    return roleRepository.save(p);
                }).orElseThrow(()->new RuntimeException("Role non trouvé"));
    }
    @Override
    public String supprimer(Long id) {
        roleRepository.deleteById(id);
        return "Role Supprimé !!";
    }
}

