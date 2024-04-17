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
    private final UserGroupService userGroupService;

    @Autowired
    public GroupService(GroupManagementService groupManagementService, UserGroupService userGroupService) {
        this.groupManagementService = groupManagementService;
        this.userGroupService = userGroupService;
    }

    public Optional<Group> findById(GroupId id) {
        return groupManagementService.findById(id);
    }

    public List<Group> getAllForUser(User user) {
        return userGroupService.getAllForUser(user);
    }

    public Group create(String name, User user) {
        var group = new Group(name);
        return userGroupService.addUser(group, user);
    }

    public Group changeName(Group group, String newName) {
        return groupManagementService.changeName(group, newName);
    }

    public void delete(Group group) {
        groupManagementService.delete(group);
    }

    public Group addUser(Group group, User user) {
        return userGroupService.addUser(group, user);
    }

    public Group removeUser(Group group, User user) {
        return userGroupService.removeUser(group, user);
    }

    public Group changeName(GroupId id, String newName) throws GroupNotFoundException {
        return groupManagementService.changeName(id, newName);
    }

    public void delete(GroupId id) throws GroupNotFoundException {
        groupManagementService.delete(id);
    }

    public Group addUser(GroupId groupId, UserId userId) throws UserNotFoundException, GroupNotFoundException {
        return userGroupService.addUser(groupId, userId);
    }

    public Group removeUser(GroupId groupId, UserId userId) throws UserNotFoundException, GroupNotFoundException {
        return userGroupService.removeUser(groupId, userId);
    }
}