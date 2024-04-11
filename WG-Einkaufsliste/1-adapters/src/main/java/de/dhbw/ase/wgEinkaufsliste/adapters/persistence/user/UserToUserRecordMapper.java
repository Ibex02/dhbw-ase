package de.dhbw.ase.wgEinkaufsliste.adapters.persistence.user;

import de.dhbw.ase.wgEinkaufsliste.domain.user.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserToUserRecordMapper implements Function<User, UserRecord> {

    @Override
    public UserRecord apply(User user) {
        return map(user);
    }

    private UserRecord map(User user) {
        return new UserRecord(user.getId(), user.getEmail(), user.getPassword(), user.getName(), user.getGroupIds());
    }
}
