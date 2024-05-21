package de.dhbw.ase.wgEinkaufsliste.application.group;

import de.dhbw.ase.wgEinkaufsliste.application.group.command.*;
import de.dhbw.ase.wgEinkaufsliste.domain.group.*;
import de.dhbw.ase.wgEinkaufsliste.domain.group.values.GroupId;
import de.dhbw.ase.wgEinkaufsliste.domain.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GroupServiceFacade implements GroupService {

    private final GroupManagementService groupManagementService;
    private final GroupUserService groupUserService;
    private final GroupRepository groupRepository;

    @Autowired
    public GroupServiceFacade(GroupManagementService groupManagementService, GroupUserService groupUserService, GroupRepository groupRepository) {
        this.groupManagementService = groupManagementService;
        this.groupUserService = groupUserService;
        this.groupRepository = groupRepository;
    }

    @Override
    public Optional<Group> findById(GroupId id) {
        return groupManagementService.findById(id);
    }

    @Override
    public Collection<Group> getAllForUser(User user) {
        return groupRepository.findAllWithUser(user);
    }

    @Override
    public Group create(CreateGroupCommand command) {
        return groupManagementService.create(command);
    }

    @Override
    public Group changeName(ChangeNameCommand command) throws GroupNotFoundException {
        return groupManagementService.changeName(command);
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