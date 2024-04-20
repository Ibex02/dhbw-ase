package de.dhbw.ase.wgEinkaufsliste.application.group;

import de.dhbw.ase.wgEinkaufsliste.application.group.command.*;
import de.dhbw.ase.wgEinkaufsliste.application.group.event.*;
import de.dhbw.ase.wgEinkaufsliste.application.user.CurrentUserProvider;
import de.dhbw.ase.wgEinkaufsliste.domain.user.UserNotFoundException;
import de.dhbw.ase.wgEinkaufsliste.domain.group.*;
import de.dhbw.ase.wgEinkaufsliste.domain.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GroupUserService {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final CurrentUserProvider currentUserProvider;

    @Autowired
    public GroupUserService(GroupRepository groupRepository, UserRepository userRepository, CurrentUserProvider currentUserProvider) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.currentUserProvider = currentUserProvider;
    }

    public List<Group> findAllWidthUser(User user) {
        return user.getGroupIds().stream().map(groupRepository::findById)
                .filter(Optional::isPresent).map(Optional::get).toList();
    }

    public Group addUserToGroup(Group group, User user) {
        group.addUser(user.getId());
        user.addToGroup(group.getId());

        userRepository.save(user);
        return groupRepository.save(group);
    }

    public Group removeUserFromGroup(Group group, User user) {
        group.removeUser(user.getId());
        user.removeFromGroup(group.getId());

        userRepository.save(user);

        if (group.isEmpty()) {
            groupRepository.deleteById(group.getId());
            return group;
        }

        return groupRepository.save(group);
    }

    public void removeUserFromAllGroup(User user) {

    }

    public Group addUserToGroup(AddUserCommand command) throws GroupNotFoundException, UserNotFoundException {
        var group = groupRepository.getById(command.groupId());
        var user = userRepository.getById(command.userId());
        return addUserToGroup(group, user);
    }

    public Group removeUserFromGroup(RemoveUserCommand command) throws GroupNotFoundException, UserNotFoundException {
        var group = groupRepository.getById(command.groupId());
        var user = userRepository.getById(command.userId());
        return removeUserFromGroup(group, user);
    }

    @EventListener
    public void handleGroupCreated(GroupCreatedEvent event) {
        var group = event.getGroup();
        var user = currentUserProvider.getUser();
        addUserToGroup(group, user);
    }

    @EventListener
    public void handleGroupDeleted(GroupDeletedEvent event) {
        var group = event.getGroup();
        var users = group.getUsersIds().stream().map(userRepository::findById).filter(Optional::isPresent).map(Optional::get).toList();
        for (var user : users) {
            user.removeFromGroup(group.getId());
            userRepository.save(user);
        }
    }
}