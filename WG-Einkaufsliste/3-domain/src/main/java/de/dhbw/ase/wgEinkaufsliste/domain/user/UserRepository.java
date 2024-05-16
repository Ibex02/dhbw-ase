package de.dhbw.ase.wgEinkaufsliste.domain.user;

import de.dhbw.ase.wgEinkaufsliste.domain.user.values.*;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(UserId id);
    Optional<User> findByEmail(Email email);
    User getById(UserId id) throws UserNotFoundException;
    User save(User user);
    void deleteById(UserId id);
}
