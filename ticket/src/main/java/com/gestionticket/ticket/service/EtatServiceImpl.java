package com.gestionticket.ticket.service;

import com.gestionticket.ticket.model.Etat;
import com.gestionticket.ticket.repository.EtatRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class EtatServiceImpl implements EtatService {

    private final EtatRepository etatRepository;

    @Override
    public Etat creer(Etat etat) {
        return etatRepository.save(etat);
    }

    @Override
    public List<Etat> Lire() {
        return etatRepository.findAll();
    }

    @Override
    public Etat modifier(Long id, Etat etat) {
        return etatRepository.findById(id)
                .map(p -> {
                    p.setLibelle(etat.getLibelle());
                    p.setDescription(etat.getDescription());
                    return etatRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Etat non trouvé"));
    }

    @Override
    public String supprimer(Long id) {
        etatRepository.deleteById(id);
        return "Etat Supprimé !!";
    }

    public Etat getEtatOuvert() {
        return etatRepository.findByLibelle("ouvert")
                .orElseThrow(() -> new RuntimeException("L'état 'ouvert' n'existe pas dans la base de données"));
    }
    public Etat getEtatEnCours() {
        return etatRepository.findByLibelle("en cours")
                .orElseThrow(() -> new RuntimeException("L'état 'ouvert' n'existe pas dans la base de données"));
    }
    public Etat getEtatResolu() {
        return etatRepository.findByLibelle("resolu")
                .orElseThrow(() -> new RuntimeException("L'état 'resolu' n'existe pas dans la base de données"));
    }
}
