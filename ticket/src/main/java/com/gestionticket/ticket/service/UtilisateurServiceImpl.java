    package com.gestionticket.ticket.service;

    import com.gestionticket.ticket.model.Role;
    import com.gestionticket.ticket.model.Utilisateur;
    import com.gestionticket.ticket.repository.RoleRepository;
    import com.gestionticket.ticket.repository.UtilisateurRepository;
    import lombok.AllArgsConstructor;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.security.core.userdetails.UserDetails;
    import org.springframework.security.core.userdetails.UserDetailsService;
    import org.springframework.security.core.userdetails.UsernameNotFoundException;
    import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.stereotype.Service;

    import java.util.List;

    @Service
    @AllArgsConstructor
    public class UtilisateurServiceImpl implements UtilisateurService {
        private final UtilisateurRepository utilisateurRepository;
        private final RoleRepository roleRepository;
       private BCryptPasswordEncoder bCryptPasswordEncoder;
        @Override
        public Utilisateur creer(Utilisateur utilisateur) {


            Long roleId = utilisateur.getRole().getId();
            Role role = roleRepository.findById(roleId)
                    .orElseThrow(() -> new RuntimeException("Le rôle spécifié n'existe pas : " + roleId));

            utilisateur.setRole(role);
           utilisateur.setMotdepasse(bCryptPasswordEncoder.encode(utilisateur.getMotdepasse()));
            return utilisateurRepository.save(utilisateur);
        }

        @Override
        public List<Utilisateur> Lire() {
            return utilisateurRepository.findAll();
        }

        @Override
        public Utilisateur modifier(Long id, Utilisateur utilisateur) {
            return utilisateurRepository.findById(id)
                    .map(p -> {
                        p.setNom(utilisateur.getNom());
                        p.setPrenom(utilisateur.getPrenom());
                        p.setEmail(utilisateur.getEmail());
                        p.setRole(utilisateur.getRole());
                        p.setMotdepasse(bCryptPasswordEncoder.encode(utilisateur.getMotdepasse()));
                        return utilisateurRepository.save(p);
                    }).orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));
        }

        @Override
        public String supprimer(Long id) {
            utilisateurRepository.deleteById(id);
            return "Utilisateur Supprimé!!";
        }


    }
