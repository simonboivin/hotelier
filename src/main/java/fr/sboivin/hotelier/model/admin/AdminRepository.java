package fr.sboivin.hotelier.model.admin;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AdminRepository extends CrudRepository<AdminEntity, Integer> {

    Optional<AdminEntity> findByUsername(String username);
}
