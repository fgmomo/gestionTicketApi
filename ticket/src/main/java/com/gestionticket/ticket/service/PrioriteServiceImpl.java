package com.gestionticket.ticket.service;
import com.gestionticket.ticket.model.Priorite;
import com.gestionticket.ticket.repository.PrioriteRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class PrioriteServiceImpl implements PrioriteService {

    private final PrioriteRepository prioriteRepository;
    @Override
    public Priorite creer(Priorite priorite) {
        return prioriteRepository.save(priorite);
    }
    @Override
    public List<Priorite> Lire() {

        return prioriteRepository.findAll();
    }
    @Override
    public Priorite modifier(Long id, Priorite priorite) {
        return prioriteRepository.findById(id)
                .map(p->{
                    p.setLibelle(priorite.getLibelle());
                    p.setDescription(priorite.getDescription());
                    return prioriteRepository.save(p);
                }).orElseThrow(()->new RuntimeException("Priorité non trouvé"));
    }
    @Override
    public String supprimer(Long id) {
        prioriteRepository.deleteById(id);
        return "Priorité Supprimé !!";
    }
}

