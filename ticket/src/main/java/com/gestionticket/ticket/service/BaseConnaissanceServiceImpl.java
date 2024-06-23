package com.gestionticket.ticket.service;

import com.gestionticket.ticket.model.BaseConnaissance;
import com.gestionticket.ticket.repository.BaseConnaissanceRepository;
import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@AllArgsConstructor

@Service
public class BaseConnaissanceServiceImpl implements BaseConnaissanceService {


    private final BaseConnaissanceRepository baseConnaissanceRepository;



    @Override
    public BaseConnaissance creer(BaseConnaissance baseConnaissance) {
        return baseConnaissanceRepository.save(baseConnaissance);
    }


    @Override
    public List<BaseConnaissance> lire() {
        return baseConnaissanceRepository.findAll();
    }

    @Override
    public BaseConnaissance modifier(Long id, BaseConnaissance baseConnaissance) {
        return baseConnaissanceRepository.findById(id)
                .map(existingBaseConnaissance -> {
                    existingBaseConnaissance.setQuestion(baseConnaissance.getQuestion());
                    existingBaseConnaissance.setReponse(baseConnaissance.getReponse());
                    existingBaseConnaissance.setLien(baseConnaissance.getLien());
                    existingBaseConnaissance.setPieceJointe(baseConnaissance.getPieceJointe());
                    return baseConnaissanceRepository.save(existingBaseConnaissance);
                }).orElseThrow(() -> new RuntimeException("Base de connaissance non trouvée"));
    }

    @Override
    public String supprimer(Long id) {
        baseConnaissanceRepository.deleteById(id);
        return "Base de connaissance supprimée !!";
    }
}
