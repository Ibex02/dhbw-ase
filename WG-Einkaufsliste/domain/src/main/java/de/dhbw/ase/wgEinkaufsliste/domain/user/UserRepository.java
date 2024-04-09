package de.dhbw.ase.wgEinkaufsliste.domain.user;

public interface UserRepository {
    User findById(String id);
    User findByEmail(String email);
    void save(User user);
    void deleteById(String id);
}
