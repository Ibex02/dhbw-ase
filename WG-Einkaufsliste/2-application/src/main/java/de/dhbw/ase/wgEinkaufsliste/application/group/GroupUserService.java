package de.dhbw.ase.wgEinkaufsliste.application.group;

import de.dhbw.ase.wgEinkaufsliste.application.group.command.*;
import de.dhbw.ase.wgEinkaufsliste.application.user.CurrentUserProvider;
import de.dhbw.ase.wgEinkaufsliste.domain.user.UserNotFoundException;
import de.dhbw.ase.wgEinkaufsliste.domain.group.*;
import de.dhbw.ase.wgEinkaufsliste.domain.user.*;
import org.springframework.beans.factory.annotation.Autowired;
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

    public void removeUserFromAllGroups(User user) {
        findAllWidthUser(user).forEach(x -> removeUserFromGroup(x, user));
    }

    public Group removeAllUsersFromGroup(Group group) {
        for (var userId : group.getUsersIds()) {
            group.removeUser(userId);

            var userOpt = userRepository.findById(userId);
            if (userOpt.isPresent()) {
                userOpt.get().removeFromGroup(group.getId());
                userRepository.save(userOpt.get());
            }
        }
        return groupRepository.save(group);
    }
}