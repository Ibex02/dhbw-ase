package de.dhbw.ase.wgEinkaufsliste.application.authentication;

import de.dhbw.ase.wgEinkaufsliste.domain.entities.User;
import de.dhbw.ase.wgEinkaufsliste.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final PasswordEncoder encoder;
    private final UserRepository repository;

    @Autowired
    public UserService(PasswordEncoder encoder, UserRepository repository) {
        this.encoder = encoder;
        this.repository = repository;
    }

    public User creatUser(String email, String password) {
        String encoded = encoder.encode(password);
        var user = new User(email, encoded, "test");
        repository.save(user);
        return user;
    }

}