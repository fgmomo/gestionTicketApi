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
    private final PrioriteRepository prioriteRepository;
    private final CategorieRepository categorieRepository;
    private final EtatService etatService;
    private final ReponseRepository reponseRepository;

    @Override
    public Ticket creer(Ticket ticket) {
        ticket.setDate_creation(LocalDateTime.now());

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
                .map(p -> {
                    p.setDescription(ticket.getDescription());
                    p.setPriorite(ticket.getPriorite());
                    p.setCategorie(ticket.getCategorie());
                    return ticketRepository.save(p);
                }).orElseThrow(() -> new RuntimeException("Ticket non trouvé"));
    }

    @Override
    public String supprimer(Long id) {
        ticketRepository.deleteById(id);
        return "Ticket Supprimé !!";
    }

    @Override
    public Ticket prendreEnCharge(Long id) {
        return ticketRepository.findById(id)
                .map(ticket -> {
                    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                    Utilisateur formateur = utilisateurRepository.findByEmail(authentication.getName())
                            .orElseThrow(() -> new RuntimeException("Formateur non trouvé : " + authentication.getName()));
                    ticket.setFormateur(formateur);
                    Etat etatEnCours = etatService.getEtatEnCours();
                    ticket.setEtat(etatEnCours);
                    return ticketRepository.save(ticket);
                }).orElseThrow(() -> new RuntimeException("Ticket non trouvé"));
    }

    @Override
    public Ticket resoudre(Long id, String reponseContenu) {
        return ticketRepository.findById(id)
                .map(ticket -> {
                    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                    Utilisateur formateur = utilisateurRepository.findByEmail(authentication.getName())
                            .orElseThrow(() -> new RuntimeException("Formateur non trouvé : " + authentication.getName()));

                    Reponse reponse = new Reponse();
                    reponse.setContenu(reponseContenu);
                    reponse.setDateCreation(LocalDateTime.now());
                    reponse.setTicket(ticket);
                    reponse.setAuteur(formateur);

                    ticket.setReponseFormateur(reponse);

                    ticket.setDate_resolution(LocalDateTime.now());
                    Etat etatResolu = etatService.getEtatResolu();
                    ticket.setEtat(etatResolu);

                    ticketRepository.save(ticket);

                    return ticket;
                }).orElseThrow(() -> new RuntimeException("Ticket non trouvé"));
    }
}
