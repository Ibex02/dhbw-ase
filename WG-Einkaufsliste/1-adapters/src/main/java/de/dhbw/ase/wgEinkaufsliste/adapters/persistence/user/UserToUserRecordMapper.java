package de.dhbw.ase.wgEinkaufsliste.adapters.persistence.user;

import de.dhbw.ase.wgEinkaufsliste.domain.group.values.GroupId;
import de.dhbw.ase.wgEinkaufsliste.domain.user.User;
import de.dhbw.ase.wgEinkaufsliste.domain.user.values.Email;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class UserToUserRecordMapper implements Function<User, UserRecord> {

    @Override
    public UserRecord apply(User user) {
        return map(user);
    }

    private UserRecord map(User user) {
        var id = user.getId().value();
        var email = user.getEmail().value();
        var groupIds = mapGroupIds(user.getGroupIds());

        return new UserRecord(id, email, user.getPassword(), user.getName(), groupIds);
    }

    private List<String> mapGroupIds(List<GroupId> ids) {
        return ids.stream().map(GroupId::value).toList();
    }
}
