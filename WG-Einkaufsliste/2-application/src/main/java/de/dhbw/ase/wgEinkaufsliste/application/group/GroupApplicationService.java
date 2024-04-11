package de.dhbw.ase.wgEinkaufsliste.application.group;

import de.dhbw.ase.wgEinkaufsliste.domain.group.Group;
import de.dhbw.ase.wgEinkaufsliste.domain.group.GroupRepository;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListRepository;
import de.dhbw.ase.wgEinkaufsliste.domain.user.User;
import de.dhbw.ase.wgEinkaufsliste.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupApplicationService {
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;
    private final ShoppingListRepository shoppingListRepository;

    @Autowired
    public GroupApplicationService(
            GroupRepository groupRepository, UserRepository userRepository,
            ShoppingListRepository shoppingListRepository) {
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
        this.shoppingListRepository = shoppingListRepository;
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

            group.removeUser(user);

            userRepository.save(user);
        }

        for (String listId : group.getListIds().stream().toList()) {
            shoppingListRepository.deleteById(listId);
        }

        groupRepository.deleteById(group.getId());
    }

    public void changeName(Group group, String name) {
        group.setName(name);

        groupRepository.save(group);
    }

    public void addUser(Group group, User user) {
        group.addUser(user);

        userRepository.save(user);
        groupRepository.save(group);
    }

    public void removeUser(Group group, User user) {
        group.removeUser(user);

        userRepository.save(user);
        groupRepository.save(group);
    }
}
