package com.gestionticket.ticket.repository;
import com.gestionticket.ticket.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByLibelle(String roleLibelle);
}
