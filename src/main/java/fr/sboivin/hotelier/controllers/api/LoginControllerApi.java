package fr.sboivin.hotelier.controllers.api;

import fr.sboivin.hotelier.model.admin.AdminEntity;
import fr.sboivin.hotelier.model.admin.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginControllerApi {

    private final AdminService adminService;

    public LoginControllerApi(AdminService adminService) {
        this.adminService = adminService;
    }


    @PostMapping(path = "", produces = "application/json")
    public ResponseEntity<AdminEntity> checkLogin(@RequestBody AdminEntity adminInput) {
        Optional<AdminEntity> adminOptional = adminService.getAdminByUsername(adminInput.getUsername());
        if (adminOptional.isPresent()) {
            AdminEntity admin = adminOptional.get();
            if (adminInput.getPassword().equals(admin.getPassword())) {
                admin.setPassword("***");
                return ResponseEntity.status(HttpStatus.OK).body(admin);
            } else {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
    }

}
