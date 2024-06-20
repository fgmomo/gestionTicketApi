package com.gestionticket.ticket.service;
import com.gestionticket.ticket.model.Categorie;
import com.gestionticket.ticket.repository.CategorieRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class CategorieServiceImpl implements CategorieService {

    private final CategorieRepository categorieRepository;
    @Override
    public Categorie creer(Categorie categorie) {
        return categorieRepository.save(categorie);
    }
    @Override
    public List<Categorie> Lire() {

        return categorieRepository.findAll();
    }
    @Override
    public Categorie modifier(Long id, Categorie categorie) {
        return categorieRepository.findById(id)
                .map(p->{
                    p.setLibelle(categorie.getLibelle());
                    p.setDescription(categorie.getDescription());
                    return categorieRepository.save(p);
                }).orElseThrow(()->new RuntimeException("Catégorie non trouvée"));
    }
    @Override
    public String supprimer(Long id) {
        categorieRepository.deleteById(id);
        return "Catégorie Supprimée !!";
    }
}

