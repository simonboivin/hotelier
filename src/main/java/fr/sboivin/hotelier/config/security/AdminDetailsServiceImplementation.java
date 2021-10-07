package fr.sboivin.hotelier.config.security;

import fr.sboivin.hotelier.model.admin.AdminEntity;
import fr.sboivin.hotelier.model.admin.AdminService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminDetailsServiceImplementation implements UserDetailsService {

    private AdminService adminService;

    public AdminDetailsServiceImplementation(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<AdminEntity> adminOptional = adminService.getAdminByUsername(s);
        if (adminOptional.isPresent()) {
            return new AdminDetailsImplementation(adminOptional.get());
        } else {
            throw new UsernameNotFoundException("Pas d'administrateur avec l'username: " + s);
        }
    }
}

