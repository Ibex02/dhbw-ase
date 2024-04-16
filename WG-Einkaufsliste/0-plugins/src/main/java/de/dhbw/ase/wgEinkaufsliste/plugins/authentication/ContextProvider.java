package de.dhbw.ase.wgEinkaufsliste.plugins.authentication;

import de.dhbw.ase.wgEinkaufsliste.application.authentication.UserContextProvider;
import de.dhbw.ase.wgEinkaufsliste.domain.user.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class ContextProvider implements UserContextProvider {

    public CustomUserDetails getUserDetails() {
        return (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

//        if (!(principal instanceof CustomUserDetails userDetails)) {
//            throw new Exception();
//        }

//        return userDetails;
    }

    @Override
    public User getUser()  {
        return getUserDetails().getUser();
    }
}
