package de.dhbw.ase.wgEinkaufsliste.adapters.representations.user;


import de.dhbw.ase.wgEinkaufsliste.adapters.representations.user.resource.UserResource;
import de.dhbw.ase.wgEinkaufsliste.domain.user.User;
import de.dhbw.ase.wgEinkaufsliste.domain.user.values.Email;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserToUserResourceMapper implements Function<User, UserResource> {

    @Override
    public UserResource apply(User user) {
        return map(user);
    }

    private UserResource map(User user) {
        var id = user.getId().value();
        var email = user.getEmail().value();

        return new UserResource(id, email, user.getName());
    }
}
