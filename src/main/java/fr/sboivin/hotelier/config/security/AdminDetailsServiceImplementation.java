package fr.sboivin.hotelier.config.security;

import fr.sboivin.hotelier.model.admin.AdminEntity;
import fr.sboivin.hotelier.model.admin.AdminRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminDetailsServiceImplementation implements UserDetailsService {


    private final AdminRepository adminRepository;

    public AdminDetailsServiceImplementation(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        Optional<AdminEntity> adminOptional = adminRepository.findByUsername(s);
        if (adminOptional.isPresent()) {
            return new AdminDetailsImplementation(adminOptional.get());
        } else {
            throw new UsernameNotFoundException("Pas d'administrateur avec l'username: " + s);
        }
    }
}

