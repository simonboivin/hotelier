package fr.sboivin.hotelier.config.init;

import fr.sboivin.hotelier.model.admin.AdminRepository;
import fr.sboivin.hotelier.model.admin.AdminService;
import fr.sboivin.hotelier.model.admin.Role;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {


    public LoadDatabase() {

    }


    @Bean
    public CommandLineRunner initAdminTable(AdminRepository adminRepository, AdminService adminService) {
        return args -> {
            if (adminRepository.count() == 0) {
                adminService.addAdmin("admin", "1234", Role.ADMIN);
            }
        };
    }

}
