package de.dhbw.ase.wgEinkaufsliste.domain.user;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(String id);
    Optional<User> findByEmail(String email);
    User save(User user);
    void deleteById(String id);
}
