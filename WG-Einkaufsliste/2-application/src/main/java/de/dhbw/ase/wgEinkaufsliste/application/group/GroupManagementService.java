package de.dhbw.ase.wgEinkaufsliste.application.group;

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
    private final GroupEventManager eventManager;

    @Autowired
    public GroupManagementService(GroupRepository groupRepository, ShoppingListRepository shoppingListRepository, GroupEventManager eventManager) {
        this.groupRepository = groupRepository;
        this.shoppingListRepository = shoppingListRepository;
        this.eventManager = eventManager;
    }

    public Optional<Group> findById(GroupId id) {
        return groupRepository.findById(id);
    }

    public Group create(String name) {
        var group = groupRepository.save(new Group(name));
        eventManager.raiseGroupCreated(group, this);
        return group;
    }

    public Group changeName(Group group, String newName) {
        group.setName(newName);
        return groupRepository.save(group);
    }

    public Group changeName(GroupId id, String newName) throws GroupNotFoundException {
        var group = groupRepository.getById(id);
        return changeName(group, newName);
    }

    public void delete(Group group) {
        group.getListIds().forEach(shoppingListRepository::deleteById);
        groupRepository.deleteById(group.getId());
        eventManager.raiseGroupDeleted(group, this);
    }

    public void delete(GroupId id) throws GroupNotFoundException {
        var group = groupRepository.getById(id);
        delete(group);
    }
}