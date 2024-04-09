package de.dhbw.ase.wgEinkaufsliste.adapters.persistence.mappers;

import de.dhbw.ase.wgEinkaufsliste.adapters.persistence.UserRecord;
import de.dhbw.ase.wgEinkaufsliste.domain.entities.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserRecordToUserMapper implements Function<UserRecord, User> {

    @Override
    public User apply(UserRecord entity) {
        return map(entity);
    }

    private User map(UserRecord entity) {
        return new User(entity.getId(), entity.getUserName(), entity.getPassword(), entity.getDisplayName(), entity.getGroups());
    }
}
