package com.gestionticket.ticket.service;

import com.gestionticket.ticket.model.*;
import com.gestionticket.ticket.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {
    private final UtilisateurRepository utilisateurRepository;
    private final TicketRepository ticketRepository;
    private final PrioriteRepository prioriteRepository; // Injecter le repository de priorite
    private final CategorieRepository categorieRepository;
    private final EtatRepository etatRepository;
    private final EtatService etatService; // Injection de EtatService
    @Override
    public Ticket creer(Ticket ticket, Long utilisateurId) {
        ticket.setDate_creation(LocalDateTime.now());

        // Récupérer l'utilisateur à partir de l'ID fourni
        Utilisateur apprenant = utilisateurRepository.findById(utilisateurId)
                .orElseThrow(() -> new RuntimeException("L'utilisateur spécifié n'existe pas : " + utilisateurId));

        // Associer l'utilisateur au ticket
        ticket.setApprenant(apprenant);

        Long prioriteId = ticket.getPriorite().getId(); // Récupère l'ID de la priorite depuis l'objet Priorite de Ticket
        Long categorieId = ticket.getCategorie().getId();
        Priorite priorite = prioriteRepository.findById(prioriteId)
                .orElseThrow(() -> new RuntimeException("La spécifié n'existe pas : " + prioriteId));
        ticket.setPriorite(priorite); // Associe le ticket avec la priorité récupérée

        Categorie categorie = categorieRepository.findById(categorieId)
                .orElseThrow(() -> new RuntimeException("La categorie n'existe pas : " + categorieId));

        ticket.setCategorie(categorie); // Associe le ticket avec la categorie récupérée


        Etat etatOuvert = etatService.getEtatOuvert();
        ticket.setEtat(etatOuvert);
        ticket.setApprenant(apprenant);
        return ticketRepository.save(ticket);


    }
    @Override
    public List<Ticket> Lire() {
        return ticketRepository.findAll();
    }
    @Override
    public Ticket modifier(Long id, Ticket ticket) {
        return ticketRepository.findById(id)
                .map(p->{
                    p.setDescription(ticket.getDescription());
                    p.setPriorite(ticket.getPriorite());
                    p.setCategorie(ticket.getCategorie());
                    return ticketRepository.save(p);
                }).orElseThrow(()->new RuntimeException("Ticket non trouvé"));
    }
    @Override
    public String supprimer(Long id) {
       ticketRepository.deleteById(id);
        return "Ticket Supprimé!!";
    }
}
