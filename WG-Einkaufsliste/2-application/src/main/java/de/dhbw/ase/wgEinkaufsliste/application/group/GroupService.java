package de.dhbw.ase.wgEinkaufsliste.application.group;

import de.dhbw.ase.wgEinkaufsliste.application.group.command.*;
import de.dhbw.ase.wgEinkaufsliste.domain.group.*;
import de.dhbw.ase.wgEinkaufsliste.domain.group.values.GroupId;
import de.dhbw.ase.wgEinkaufsliste.domain.user.*;

import java.util.*;

public interface GroupService {
    Optional<Group> findById(GroupId id);

    Collection<Group> getAllForUser(User user);

    Group create(CreateGroupCommand command);

    Group changeName(ChangeNameCommand command) throws GroupNotFoundException;

    void delete(GroupId id) throws GroupNotFoundException;

    Group addUser(AddUserCommand command) throws UserNotFoundException, GroupNotFoundException;

    Group removeUser(RemoveUserCommand command) throws UserNotFoundException, GroupNotFoundException;
}
