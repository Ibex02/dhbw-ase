package de.dhbw.ase.wgEinkaufsliste.application.group;

import de.dhbw.ase.wgEinkaufsliste.application.group.command.*;
import de.dhbw.ase.wgEinkaufsliste.domain.user.UserNotFoundException;
import de.dhbw.ase.wgEinkaufsliste.domain.group.*;
import de.dhbw.ase.wgEinkaufsliste.domain.user.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupUserService {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    @Autowired
    public GroupUserService(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    public Group addUserToGroup(Group group, User user) {
        group.addUser(user.getId());
        user.addToGroup(group.getId());

        userRepository.save(user);
        return groupRepository.save(group);
    }

    public Group addUserToGroup(AddUserCommand command) throws GroupNotFoundException, UserNotFoundException {
        var group = groupRepository.getById(command.groupId());
        var user = userRepository.getById(command.userId());
        return addUserToGroup(group, user);
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

    public Group removeUserFromGroup(RemoveUserCommand command) throws GroupNotFoundException, UserNotFoundException {
        var group = groupRepository.getById(command.groupId());
        var user = userRepository.getById(command.userId());

        user.removeFromGroup(group.getId());
        userRepository.save(user);

        if (group.isEmpty()) {
            groupRepository.deleteById(group.getId());
            return group;
        }

        return groupRepository.save(group);
    }

    public void removeUserFromAllGroups(User user) {
        for (var group : groupRepository.findAllWithUser(user)) {

            user.removeFromGroup(group.getId());

            if (group.isEmpty()) {
                groupRepository.deleteById(group.getId());
            }

            groupRepository.save(group);
        }

        userRepository.save(user);
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