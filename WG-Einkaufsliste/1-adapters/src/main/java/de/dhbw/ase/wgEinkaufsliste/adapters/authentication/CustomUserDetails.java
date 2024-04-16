package de.dhbw.ase.wgEinkaufsliste.adapters.authentication;

import de.dhbw.ase.wgEinkaufsliste.domain.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import static org.springframework.security.core.userdetails.User.builder;

public class CustomUserDetails implements UserDetails {

    private final User user;

    private final UserDetails userDetails;

    public CustomUserDetails(User user) {
        this.user = user;
        this.userDetails = builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .build();
    }

    public User getUser() {
        return user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userDetails.getAuthorities();
    }

    @Override
    public String getPassword() {
        return userDetails.getPassword();
    }

    @Override
    public String getUsername() {
        return userDetails.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return userDetails.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return userDetails.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return userDetails.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return userDetails.isEnabled();
    }
}
