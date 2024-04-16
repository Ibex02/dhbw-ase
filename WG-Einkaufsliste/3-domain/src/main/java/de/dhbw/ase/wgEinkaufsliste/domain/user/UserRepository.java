package de.dhbw.ase.wgEinkaufsliste.domain.user;

import de.dhbw.ase.wgEinkaufsliste.domain.user.values.UserId;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(UserId id);
    Optional<User> findByEmail(String email);
    User save(User user);
    void deleteById(UserId id);
}