package com.gestionticket.ticket.service;

import com.gestionticket.ticket.model.BaseConnaissance;
import com.gestionticket.ticket.repository.BaseConnaissanceRepository;

import java.util.List;

public interface BaseConnaissanceService {
    BaseConnaissance creer(BaseConnaissance baseConnaissance);
    List<BaseConnaissance> lire();
    BaseConnaissance modifier(Long id, BaseConnaissance baseConnaissance);
    String supprimer(Long id);


}
