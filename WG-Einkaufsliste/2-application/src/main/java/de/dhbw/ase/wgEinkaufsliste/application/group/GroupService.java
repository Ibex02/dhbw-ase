package de.dhbw.ase.wgEinkaufsliste.application.group;

import de.dhbw.ase.wgEinkaufsliste.application.group.command.*;
import de.dhbw.ase.wgEinkaufsliste.domain.group.*;
import de.dhbw.ase.wgEinkaufsliste.domain.group.values.GroupId;
import de.dhbw.ase.wgEinkaufsliste.domain.user.*;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GroupService {

    private final GroupManagementService groupManagementService;
    private final GroupUserService groupUserService;
    private final GroupRepository groupRepository;

    public GroupService(GroupManagementService groupManagementService, GroupUserService groupUserService, GroupRepository groupRepository) {
        this.groupManagementService = groupManagementService;
        this.groupUserService = groupUserService;
        this.groupRepository = groupRepository;
    }

    public Optional<Group> findById(GroupId id) {
        return groupManagementService.findById(id);
    }

    public Collection<Group> getAllForUser(User user) {
        return groupRepository.findAllWithUser(user);
    }

    public Group create(CreateGroupCommand command) {
        return groupManagementService.create(command);
    }

    public Group changeName(ChangeNameCommand command) throws GroupNotFoundException {
        return groupManagementService.changeName(command);
    }

    public void delete(GroupId id) throws GroupNotFoundException {
        groupManagementService.delete(id);
    }

    public Group addUser(AddUserCommand command) throws UserNotFoundException, GroupNotFoundException {
        return groupUserService.addUserToGroup(command);
    }

    public Group removeUser(RemoveUserCommand command) throws UserNotFoundException, GroupNotFoundException {
        return groupUserService.removeUserFromGroup(command);
    }
}