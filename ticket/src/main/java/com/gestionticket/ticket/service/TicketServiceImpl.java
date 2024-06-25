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
    private final EmailService emailService;
    private final RoleRepository roleRepository;
    private final ReponseService reponseService;


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

        Ticket nouveauTicket = ticketRepository.save(ticket);

        Role role = roleRepository.findByLibelle("Formateur");

        List<Utilisateur> formateurs = utilisateurRepository.findAllByRole(role);
        formateurs.forEach(formateur -> {
            String sujet = "Nouveau ticket soumis";
            String corps = "Un nouveau ticket a été soumis par l'apprenant " + apprenant.getPrenom() + " " + apprenant.getNom() + ". Veuillez le vérifier.";
            emailService.sendSimpleMessage(formateur.getEmail(), sujet, corps);
        });

        String sujet = "Nouveau ticket soumis";
        String corps = "Votre ticket a été soumis avec succès. Patientez...";
        emailService.sendSimpleMessage(apprenant.getEmail(), sujet, corps);

        return nouveauTicket;
    }

    @Override
    public List<Ticket> Lire() {
        return ticketRepository.findAll();
    }

    @Override
    public Ticket modifier(Long id, Ticket ticketModifie) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailUtilisateurConnecte = authentication.getName();

        return ticketRepository.findById(id)
                .map(ticket -> {
                    Utilisateur proprietaireTicket = ticket.getApprenant();

                    if (!proprietaireTicket.getEmail().equals(emailUtilisateurConnecte)) {
                        throw new RuntimeException("Vous n'êtes pas autorisé à modifier ce ticket.");
                    }

                    if (ticket.getEtat().getLibelle().equals("en cours") || ticket.getEtat().getLibelle().equals("resolu")) {
                        throw new RuntimeException("Ce ticket ne peut plus être modifié.");
                    }

                    ticket.setDescription(ticketModifie.getDescription());
                    ticket.setPriorite(ticketModifie.getPriorite());
                    ticket.setCategorie(ticketModifie.getCategorie());

                    return ticketRepository.save(ticket);
                })
                .orElseThrow(() -> new RuntimeException("Ticket non trouvé avec l'ID : " + id));
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
                    if (ticket.getEtat().getLibelle().equals("en cours") || ticket.getEtat().getLibelle().equals("resolu")) {
                        throw new RuntimeException("Ce ticket est déjà pris en charge ou résolu.");
                    }

                    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                    Utilisateur formateur = utilisateurRepository.findByEmail(authentication.getName())
                            .orElseThrow(() -> new RuntimeException("Formateur non trouvé : " + authentication.getName()));
                    ticket.setFormateur(formateur);
                    Etat etatEnCours = etatService.getEtatEnCours();
                    ticket.setEtat(etatEnCours);
                    Ticket updatedTicket = ticketRepository.save(ticket);

                    String sujet = "Ticket pris en charge";
                    String corps = "Votre ticket N°" + ticket.getId() + " a été pris en charge par " + formateur.getNom() + " " + formateur.getPrenom() + ".";
                    emailService.sendSimpleMessage(ticket.getApprenant().getEmail(), sujet, corps);

                    return updatedTicket;
                }).orElseThrow(() -> new RuntimeException("Ticket non trouvé"));
    }

    @Override
    public Ticket resoudre(Long id, String reponseContenu) {
        return ticketRepository.findById(id)
                .map(ticket -> {
                    Etat etatResolu = etatService.getEtatResolu();
                    if (ticket.getEtat().equals(etatResolu)) {
                        throw new RuntimeException("Le ticket a déjà été résolu.");
                    }

                    if (!ticket.getEtat().getLibelle().equals("en cours")) {
                        throw new RuntimeException("Le ticket doit être pris en charge avant d'être résolu.");
                    }

                    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                    Utilisateur formateur = utilisateurRepository.findByEmail(authentication.getName())
                            .orElseThrow(() -> new RuntimeException("Formateur non trouvé : " + authentication.getName()));

                    Reponse reponse = new Reponse();
                    reponse.setContenu(reponseContenu);
                    reponse.setDateCreation(LocalDateTime.now());
                    reponse.setTicket(ticket);
                    reponse.setAuteur(formateur);

                    reponseRepository.save(reponse);

                    ticket.setReponseFormateur(reponse);
                    ticket.setDate_resolution(LocalDateTime.now());
                    ticket.setEtat(etatResolu);

                    ticketRepository.save(ticket);

                    String sujet = "Ticket résolu";
                    String corps = "Le ticket " + ticket.getId() + " a été résolu par " + formateur.getNom() + " " + formateur.getPrenom() + ".";
                    emailService.sendSimpleMessage(ticket.getApprenant().getEmail(), sujet, corps);

                    return ticket;
                }).orElseThrow(() -> new RuntimeException("Ticket non trouvé"));
    }

}
