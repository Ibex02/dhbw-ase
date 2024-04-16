package de.dhbw.ase.wgEinkaufsliste.adapters.persistence.user;

import de.dhbw.ase.wgEinkaufsliste.domain.user.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserRecordToUserMapper implements Function<UserRecord, User> {

    @Override
    public User apply(UserRecord entity) {
        return map(entity);
    }

    private User map(UserRecord entity) {
        return new User(entity.id(), entity.email(), entity.password(), entity.name(), entity.groups());
    }
}
