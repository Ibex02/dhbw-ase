package de.dhbw.ase.wgEinkaufsliste.application.authorization;

import de.dhbw.ase.wgEinkaufsliste.domain.group.Group;
import de.dhbw.ase.wgEinkaufsliste.domain.user.User;
import org.springframework.stereotype.Service;

@Service
public class UserAuthorizationService {

    public boolean isInGroup(User user, Group group) {
        return isInGroup(user, group.getId());
    }

    public boolean isInGroup(User user, String groupId) {
        return user.getGroupIds().contains(groupId);
    }

}
