// CustomUserDetailsService.java
package com.gestionticket.ticket.service;

import com.gestionticket.ticket.model.Utilisateur;
import com.gestionticket.ticket.repository.UtilisateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UtilisateurRepository utilisateurRepository;

    @Autowired
    public CustomUserDetailsService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Utilisateur> utilisateurOptional = utilisateurRepository.findByEmail(username);
        Utilisateur utilisateur = utilisateurOptional.orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé: " + username));
        return utilisateur;
    }
}
