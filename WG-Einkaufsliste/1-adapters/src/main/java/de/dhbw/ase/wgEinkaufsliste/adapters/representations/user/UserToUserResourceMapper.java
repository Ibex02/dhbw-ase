package de.dhbw.ase.wgEinkaufsliste.adapters.representations.user;


import de.dhbw.ase.wgEinkaufsliste.domain.user.User;
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
        return new UserResource(id, user.getEmail(), user.getName());
    }
}
