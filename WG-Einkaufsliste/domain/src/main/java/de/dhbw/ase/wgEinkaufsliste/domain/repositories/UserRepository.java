package de.dhbw.ase.wgEinkaufsliste.domain.repositories;

import de.dhbw.ase.wgEinkaufsliste.domain.entities.User;

public interface UserRepository {
    User findUserByEmail(String email);
    void save(User user);
}
