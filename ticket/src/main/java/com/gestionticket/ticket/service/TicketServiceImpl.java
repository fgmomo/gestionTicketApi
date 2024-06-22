package com.gestionticket.ticket.service;

import com.gestionticket.ticket.model.*;
import com.gestionticket.ticket.repository.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final EtatService etatService;
    @Override
    public Ticket creer(Ticket ticket) {
        ticket.setDate_creation(LocalDateTime.now());

        // Récupérer l'utilisateur apprenant à partir du contexte de sécurité
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Utilisateur apprenant = utilisateurRepository.findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé : " + authentication.getName()));
        ticket.setApprenant(apprenant);

        Long prioriteId = ticket.getPriorite().getId();
        Long categorieId = ticket.getCategorie().getId();

        Priorite priorite = prioriteRepository.findById(prioriteId)
                .orElseThrow(() -> new RuntimeException("La priorité spécifiée n'existe pas : " + prioriteId));
        ticket.setPriorite(priorite);

        Categorie categorie = categorieRepository.findById(categorieId)
                .orElseThrow(() -> new RuntimeException("La catégorie spécifiée n'existe pas : " + categorieId));
        ticket.setCategorie(categorie);

        Etat etatOuvert = etatService.getEtatOuvert();
        ticket.setEtat(etatOuvert);

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
        return "Ticket Supprimé !!";
    }
}
