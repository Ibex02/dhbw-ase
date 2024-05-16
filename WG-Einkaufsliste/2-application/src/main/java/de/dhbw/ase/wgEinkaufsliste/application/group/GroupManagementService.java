package de.dhbw.ase.wgEinkaufsliste.application.group;

import de.dhbw.ase.wgEinkaufsliste.application.group.command.*;
import de.dhbw.ase.wgEinkaufsliste.domain.group.Group;
import de.dhbw.ase.wgEinkaufsliste.domain.group.GroupNotFoundException;
import de.dhbw.ase.wgEinkaufsliste.domain.group.GroupRepository;
import de.dhbw.ase.wgEinkaufsliste.domain.group.values.GroupId;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupManagementService {
    private final GroupRepository groupRepository;
    private final ShoppingListRepository shoppingListRepository;
    private final GroupUserService groupUserService;

    @Autowired
    public GroupManagementService(GroupRepository groupRepository, ShoppingListRepository shoppingListRepository, GroupUserService groupUserService) {
        this.groupRepository = groupRepository;
        this.shoppingListRepository = shoppingListRepository;
        this.groupUserService = groupUserService;
    }

    public Optional<Group> findById(GroupId id) {
        return groupRepository.findById(id);
    }

    public Group create(CreateGroupCommand command) {
        var group = new Group(command.name());
        return groupUserService.addUserToGroup(group, command.initialUser());
    }

    public Group changeName(ChangeNameCommand command) throws GroupNotFoundException {
        var group = groupRepository.getById(command.groupId());
        group.setName(command.newName());
        return groupRepository.save(group);
    }

    public void delete(GroupId id) throws GroupNotFoundException {
        var group = groupRepository.getById(id);

        groupUserService.removeAllUsersFromGroup(group);

        group.getListIds().forEach(shoppingListRepository::deleteById);
        groupRepository.deleteById(group.getId());
    }
}