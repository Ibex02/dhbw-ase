package de.dhbw.ase.wgEinkaufsliste.application.authorization;

import de.dhbw.ase.wgEinkaufsliste.domain.group.Group;
import de.dhbw.ase.wgEinkaufsliste.domain.group.values.GroupId;
import de.dhbw.ase.wgEinkaufsliste.domain.user.User;
import org.springframework.stereotype.Service;

@Service
public class UserAuthorizationService {

    public boolean isInGroup(User user, Group group) {
        return isInGroup(user, group.getId());
    }

    public boolean isInGroup(User user, GroupId groupId) {
        return user.getGroupIds().contains(groupId);
    }

}
