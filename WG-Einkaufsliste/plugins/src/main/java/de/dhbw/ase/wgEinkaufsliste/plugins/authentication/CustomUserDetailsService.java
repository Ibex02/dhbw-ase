package de.dhbw.ase.wgEinkaufsliste.plugins.authentication;

import de.dhbw.ase.wgEinkaufsliste.adapters.authentication.mappers.UserToUserDetailsMapper;
import de.dhbw.ase.wgEinkaufsliste.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userDetailsRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userDetailsRepository.findUserByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }

        // Create and return the Spring Security UserDetails object
        return org.springframework.security.core.userdetails.User.builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles("ADMIN") // Replace with appropriate roles from userDetails if available
                .build();
    }
}
