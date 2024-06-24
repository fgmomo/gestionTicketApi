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

        Role role =  roleRepository.findByLibelle("Formateur");

        List<Utilisateur> formateurs = utilisateurRepository.findAllByRole(role);
        formateurs.forEach(formateur -> {
            String sujet = "Nouveau ticket soumis";
            String corps = "Un nouveau ticket a été soumis par l'apprenant " + apprenant.getPrenom()+" " + apprenant.getNom() +".Veuillez le vérifier.";
            emailService.sendSimpleMessage( formateur.getEmail(), sujet, corps);
        });


        String sujet = "Nouveau ticket soumis";
        String corps = "Votre ticket a été soumis avec succès. Patientez..";
        emailService.sendSimpleMessage( apprenant.getEmail(), sujet, corps);

        return nouveauTicket;
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
                    Ticket updatedTicket = ticketRepository.save(ticket);


                    String sujet = "Ticket pris en charge";
                    String corps = "Le ticket N°" + ticket.getId() + " a été pris en charge par le formateur" + formateur.getNom() + ".";
                    emailService.sendSimpleMessage( ticket.getApprenant().getEmail(), sujet, corps);

                    return updatedTicket;
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

                    reponseRepository.save(reponse);


                    // Envoyer une notification par email à l'apprenant concerné
                    String sujet = "Ticket résolu";
                    String corps = "Le ticket " + ticket.getId() + " a été résolu par le formateur" + formateur.getPrenom()+ " "+formateur.getNom() +".";
                    emailService.sendSimpleMessage( ticket.getApprenant().getEmail(), sujet, corps);

                    return ticket;
                }).orElseThrow(() -> new RuntimeException("Ticket non trouvé"));
    }
}
