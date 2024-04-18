package de.dhbw.ase.wgEinkaufsliste.application.group;

import de.dhbw.ase.wgEinkaufsliste.application.group.command.AddUserCommand;
import de.dhbw.ase.wgEinkaufsliste.application.group.command.RemoveUserCommand;
import de.dhbw.ase.wgEinkaufsliste.domain.group.Group;
import de.dhbw.ase.wgEinkaufsliste.domain.group.GroupNotFoundException;
import de.dhbw.ase.wgEinkaufsliste.domain.group.values.GroupId;
import de.dhbw.ase.wgEinkaufsliste.domain.user.User;
import de.dhbw.ase.wgEinkaufsliste.domain.user.UserNotFoundException;
import de.dhbw.ase.wgEinkaufsliste.domain.user.values.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupServiceFacade implements GroupService {

    private final GroupManagementService groupManagementService;
    private final GroupUserService groupUserService;

    @Autowired
    public GroupServiceFacade(GroupManagementService groupManagementService, GroupUserService groupUserService) {
        this.groupManagementService = groupManagementService;
        this.groupUserService = groupUserService;
    }

    @Override
    public Optional<Group> findById(GroupId id) {
        return groupManagementService.findById(id);
    }

    @Override
    public List<Group> getAllForUser(User user) {
        return groupUserService.getAllForUser(user);
    }

    @Override
    public Group createWithUser(String name, User user) {
        var group = groupManagementService.create(name);
        return groupUserService.addUserToGroup(group, user);
    }

    @Override
    public Group changeName(GroupId id, String newName) throws GroupNotFoundException {
        return groupManagementService.changeName(id, newName);
    }

    @Override
    public void delete(GroupId id) throws GroupNotFoundException {
        groupManagementService.delete(id);
    }

    @Override
    public Group addUser(AddUserCommand command) throws UserNotFoundException, GroupNotFoundException {
        return groupUserService.addUserToGroup(command);
    }

    @Override
    public Group removeUser(RemoveUserCommand command) throws UserNotFoundException, GroupNotFoundException {
        return groupUserService.removeUserFromGroup(command);
    }
}