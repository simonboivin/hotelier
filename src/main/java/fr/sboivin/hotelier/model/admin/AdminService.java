package fr.sboivin.hotelier.model.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private AdminRepository adminRepository;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public Iterable<AdminEntity> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Optional<AdminEntity> getAdminById(Integer id) {
        return adminRepository.findById(id);
    }

    private AdminEntity setAdmin(AdminEntity admin, String username, String password, Role role) {
        admin.setUsername(username);
        admin.setPassword(encoder.encode(password));
        admin.setRole(role);
        return admin;
    }

    @Transactional
    public AdminEntity addAdmin(String username, String password, Role role) {
        AdminEntity admin = new AdminEntity();
        admin = setAdmin(admin, username, password, role);
        adminRepository.save(admin);
        return admin;
    }

    public Optional<AdminEntity> getAdminByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

}

