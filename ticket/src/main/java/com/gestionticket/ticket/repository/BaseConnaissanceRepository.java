package com.gestionticket.ticket.repository;

import com.gestionticket.ticket.model.BaseConnaissance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BaseConnaissanceRepository extends JpaRepository<BaseConnaissance,Long> {
    BaseConnaissance findBaseConnaissanceByquestion(String question);
}
