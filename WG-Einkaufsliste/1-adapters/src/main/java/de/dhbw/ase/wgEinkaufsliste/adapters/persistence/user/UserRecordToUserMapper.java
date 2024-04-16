package de.dhbw.ase.wgEinkaufsliste.adapters.persistence.user;

import de.dhbw.ase.wgEinkaufsliste.domain.group.values.GroupId;
import de.dhbw.ase.wgEinkaufsliste.domain.user.User;
import de.dhbw.ase.wgEinkaufsliste.domain.user.values.UserId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class UserRecordToUserMapper implements Function<UserRecord, User> {

    @Override
    public User apply(UserRecord record) {
        return map(record);
    }

    private User map(UserRecord record) {
        var id = new UserId(record.id());
        var groups = mapGroupIds(record.groups());

        return new User(id, record.email(), record.password(), record.name(), groups);
    }

    private List<GroupId> mapGroupIds(List<String> ids) {
        return ids.stream().map(GroupId::new).toList();
    }
}
