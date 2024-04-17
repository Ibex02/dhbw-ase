package de.dhbw.ase.wgEinkaufsliste.application.group;

import de.dhbw.ase.wgEinkaufsliste.domain.user.UserNotFoundException;
import de.dhbw.ase.wgEinkaufsliste.domain.group.Group;
import de.dhbw.ase.wgEinkaufsliste.domain.group.GroupNotFoundException;
import de.dhbw.ase.wgEinkaufsliste.domain.group.GroupRepository;
import de.dhbw.ase.wgEinkaufsliste.domain.group.values.GroupId;
import de.dhbw.ase.wgEinkaufsliste.domain.user.UserRepository;
import de.dhbw.ase.wgEinkaufsliste.domain.user.User;
import de.dhbw.ase.wgEinkaufsliste.domain.user.values.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupUserService {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    @Autowired
    public GroupUserService(GroupRepository groupRepository, UserRepository userRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    public List<Group> getAllForUser(User user) {
        return user.getGroupIds().stream()
                .map(groupRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get).toList();
    }

    public User getById(UserId id) throws UserNotFoundException {
        return userRepository.getById(id);
    }

    public Group addUser(Group group, User user) {
        group.addUser(user);
        userRepository.save(user);
        return groupRepository.save(group);
    }

    public Group removeUser(Group group, User user) {
        group.removeUser(user);
        userRepository.save(user);

        if (group.isEmpty()) {
            groupRepository.deleteById(group.getId());
            return group;
        }

        return groupRepository.save(group);
    }

    public Group addUser(GroupId groupId, UserId userId) throws GroupNotFoundException, UserNotFoundException {
        var group = groupRepository.getById(groupId);
        var user = userRepository.getById(userId);
        return addUser(group, user);
    }

    public Group removeUser(GroupId groupId, UserId userId) throws GroupNotFoundException, UserNotFoundException {
        var group = groupRepository.getById(groupId);
        var user = userRepository.getById(userId);
        return removeUser(group, user);
    }
}