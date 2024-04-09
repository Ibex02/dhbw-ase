package de.dhbw.ase.wgEinkaufsliste.domain.user;

public interface UserRepository {
    User findUserByEmail(String email);
    void save(User user);
}
