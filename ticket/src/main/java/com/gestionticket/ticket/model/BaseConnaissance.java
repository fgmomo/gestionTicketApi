package com.gestionticket.ticket.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "baseconnaissance")
    public class BaseConnaissance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String question;
    @Column(nullable = false)
    private String reponse;
    private String ressource;
    private Date date_update;
}
