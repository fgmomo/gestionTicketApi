package com.gestionticket.ticket.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "base_connaissance")
public class BaseConnaissance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String question;

    @Column(nullable = false)
    @Lob
    private String reponse;

    @Column(length = 255)
    private String lien;

    @Column(length = 255)
    private String pieceJointe;

    private Date dateUpdate;

    @PreUpdate
    protected void onUpdate() {
        dateUpdate = new Date();
    }

}
