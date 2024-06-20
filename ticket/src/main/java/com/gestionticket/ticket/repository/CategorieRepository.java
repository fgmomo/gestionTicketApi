package com.gestionticket.ticket.repository;

import com.gestionticket.ticket.model.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie,Long> {
        Categorie findByLibelle(String categorieLibelle);
}