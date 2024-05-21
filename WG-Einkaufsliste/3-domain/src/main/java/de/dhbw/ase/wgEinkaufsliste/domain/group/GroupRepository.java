package de.dhbw.ase.wgEinkaufsliste.domain.group;

import de.dhbw.ase.wgEinkaufsliste.domain.group.values.GroupId;
import de.dhbw.ase.wgEinkaufsliste.domain.user.User;

import java.util.*;

public interface GroupRepository {
    Optional<Group> findById(GroupId id);
    Collection<Group> findAllWithUser(User user);
    Group getById(GroupId id) throws GroupNotFoundException;
    Group save(Group group);
    void deleteById(GroupId id);
}
