package de.dhbw.ase.wgEinkaufsliste.application.group;

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
public class GroupService {
    private final GroupManagementService groupManagementService;
    private final GroupUserService groupUserService;

    @Autowired
    public GroupService(GroupManagementService groupManagementService, GroupUserService groupUserService) {
        this.groupManagementService = groupManagementService;
        this.groupUserService = groupUserService;
    }

    public Optional<Group> findById(GroupId id) {
        return groupManagementService.findById(id);
    }

    public List<Group> getAllForUser(User user) {
        return groupUserService.getAllForUser(user);
    }

    public Group createWithUser(String name, User user) {
        var group = groupManagementService.create(name);
        return groupUserService.addUser(group, user);
    }

    public Group changeName(GroupId id, String newName) throws GroupNotFoundException {
        return groupManagementService.changeName(id, newName);
    }

    public void delete(GroupId id) throws GroupNotFoundException {
        groupManagementService.delete(id);
    }

    public Group addUser(GroupId groupId, UserId userId) throws UserNotFoundException, GroupNotFoundException {
        return groupUserService.addUser(groupId, userId);
    }

    public Group removeUser(GroupId groupId, UserId userId) throws UserNotFoundException, GroupNotFoundException {
        return groupUserService.removeUser(groupId, userId);
    }
}