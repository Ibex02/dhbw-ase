package de.dhbw.ase.wgEinkaufsliste.plugins.authentication;

import de.dhbw.ase.wgEinkaufsliste.domain.user.User;
import de.dhbw.ase.wgEinkaufsliste.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserResolver {
    private final UserRepository userRepository;

    @Autowired
    public UserResolver(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(Authentication auth) {
        return userRepository.findByEmail(auth.getName());
    }
}
