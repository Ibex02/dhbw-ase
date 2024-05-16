package de.dhbw.ase.wgEinkaufsliste.application.group;

import de.dhbw.ase.wgEinkaufsliste.application.group.command.*;
import de.dhbw.ase.wgEinkaufsliste.domain.group.Group;
import de.dhbw.ase.wgEinkaufsliste.domain.group.GroupNotFoundException;
import de.dhbw.ase.wgEinkaufsliste.domain.group.values.GroupId;
import de.dhbw.ase.wgEinkaufsliste.domain.user.User;
import de.dhbw.ase.wgEinkaufsliste.domain.user.UserNotFoundException;

import java.util.List;
import java.util.Optional;

public interface GroupService {
    Optional<Group> findById(GroupId id);

    List<Group> getAllForUser(User user);

    Group create(CreateGroupCommand command);

    Group changeName(ChangeNameCommand command) throws GroupNotFoundException;

    void delete(GroupId id) throws GroupNotFoundException;

    Group addUser(AddUserCommand command) throws UserNotFoundException, GroupNotFoundException;

    Group removeUser(RemoveUserCommand command) throws UserNotFoundException, GroupNotFoundException;
}
