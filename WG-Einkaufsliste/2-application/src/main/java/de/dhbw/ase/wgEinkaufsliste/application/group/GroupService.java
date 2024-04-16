package de.dhbw.ase.wgEinkaufsliste.application.group;

import de.dhbw.ase.wgEinkaufsliste.application.user.UserNotFoundException;
import de.dhbw.ase.wgEinkaufsliste.domain.group.Group;
import de.dhbw.ase.wgEinkaufsliste.domain.group.GroupRepository;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListRepository;
import de.dhbw.ase.wgEinkaufsliste.domain.user.User;
import de.dhbw.ase.wgEinkaufsliste.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GroupService {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final ShoppingListRepository shoppingListRepository;

    @Autowired
    public GroupService(
            GroupRepository groupRepository, UserRepository userRepository,
            ShoppingListRepository shoppingListRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.shoppingListRepository = shoppingListRepository;
    }

    public Optional<Group> getById(String id) {
        return groupRepository.findById(id);
    }

    public List<Group> getAllForUser(User user) {
        var result = new ArrayList<Group>();

        for (var groupId : user.getGroupIds()) {
            var group = groupRepository.findById(groupId);

            group.ifPresent(result::add);
        }

        return result;
    }

    public Group create(User user, String name) {
        Group group = new Group(name);

        group.addUser(user);

        groupRepository.save(group);
        userRepository.save(user);

        return group;
    }

    public void delete(Group group) {
        for (String userId : group.getUsersIds().stream().toList()) {
            var user = userRepository.findById(userId);

            if (user.isPresent()) {
                group.removeUser(user.get());
                userRepository.save(user.get());
            }
        }

        for (String listId : group.getListIds()) {
            shoppingListRepository.deleteById(listId);
        }

        groupRepository.deleteById(group.getId());
    }

    public void deleteById(String id) {
        var group = groupRepository.findById(id);
        group.ifPresent(this::delete);
    }

    public Group changeName(Group group, String newName) {
        group.setName(newName);
        return groupRepository.save(group);
    }

    public Group changeNameById(String groupId, String newName) throws GroupNotFoundException {
        var group = groupRepository.findById(groupId);

        if (group.isEmpty()) {
            throw new GroupNotFoundException(groupId);
        }

        return changeName(group.get(), newName);
    }

    public Group addUser(Group group, User user) {
        group.addUser(user);

        userRepository.save(user);
        return groupRepository.save(group);
    }

    public Group addUserById(String groupId, String userId) throws GroupNotFoundException, UserNotFoundException {
        var group = groupRepository.findById(groupId);
        var user = userRepository.findById(userId);

        if (group.isEmpty()) {
            throw new GroupNotFoundException(groupId);
        }
        if (user.isEmpty()) {
            throw new UserNotFoundException(userId);
        }

        return addUser(group.get(), user.get());
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

    public Group removeUserById(String groupId, String userId) throws GroupNotFoundException, UserNotFoundException {
        var group = groupRepository.findById(groupId);
        var user = userRepository.findById(userId);

        if (group.isEmpty()) {
            throw new GroupNotFoundException(groupId);
        }
        if (user.isEmpty()) {
            throw new UserNotFoundException(userId);
        }

        return removeUser(group.get(), user.get());
    }
}
