package de.dhbw.ase.wgEinkaufsliste.plugins.authentication;

import de.dhbw.ase.wgEinkaufsliste.application.user.CurrentUserProvider;
import de.dhbw.ase.wgEinkaufsliste.domain.user.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ContextProvider implements CurrentUserProvider {

    public CustomUserDetails getUserDetails() {
        return (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    @Override
    public User getUser()  {
        return getUserDetails().getUser();
    }
}