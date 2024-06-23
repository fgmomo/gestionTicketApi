package com.gestionticket.ticket.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDateTime date_creation;

    private LocalDateTime date_resolution;

    @ManyToOne
    private Etat etat;

    @ManyToOne
    private Priorite priorite;

    @ManyToOne
    private Categorie categorie;

    @ManyToOne
    private Utilisateur formateur;

    @ManyToOne
    private Utilisateur apprenant;


    @ManyToOne(cascade = CascadeType.ALL)
    private Reponse reponseFormateur;
}
