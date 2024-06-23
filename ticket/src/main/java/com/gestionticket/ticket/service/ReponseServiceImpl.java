package com.gestionticket.ticket.service;

import com.gestionticket.ticket.model.Reponse;
import com.gestionticket.ticket.repository.ReponseRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ReponseServiceImpl implements ReponseService {

    private final ReponseRepository reponseRepository;

    @Override
    public Reponse creer(Reponse reponse) {
        reponse.setDateCreation(LocalDateTime.now());
        return reponseRepository.save(reponse);
    }

    @Override
    public List<Reponse> Lire() {
        return reponseRepository.findAll();
    }

    @Override
    public Reponse modifier(Long id, Reponse reponse) {
        return reponseRepository.findById(id)
                .map(existingReponse -> {
                    existingReponse.setContenu(reponse.getContenu());
                    return reponseRepository.save(existingReponse);
                })
                .orElseThrow(() -> new RuntimeException("Réponse non trouvée avec l'ID : " + id));
    }

    @Override
    public String supprimer(Long id) {
        reponseRepository.deleteById(id);
        return "Réponse supprimée avec succès";
    }
}
