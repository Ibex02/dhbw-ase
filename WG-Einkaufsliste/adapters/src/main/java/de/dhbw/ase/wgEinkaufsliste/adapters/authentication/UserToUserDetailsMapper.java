package de.dhbw.ase.wgEinkaufsliste.adapters.authentication;

import de.dhbw.ase.wgEinkaufsliste.adapters.authentication.CustomUserDetails;
import de.dhbw.ase.wgEinkaufsliste.domain.user.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserToUserDetailsMapper implements Function<User, UserDetails> {

    @Override
    public UserDetails apply(User user) {
        return map(user);
    }

    private UserDetails map(User user) {
        if (user == null) return null;
        return new CustomUserDetails(user);
    }
}
