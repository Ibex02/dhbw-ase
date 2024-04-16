package de.dhbw.ase.wgEinkaufsliste.application.shoppingList;

import de.dhbw.ase.wgEinkaufsliste.application.group.GroupNotFoundException;
import de.dhbw.ase.wgEinkaufsliste.domain.group.Group;
import de.dhbw.ase.wgEinkaufsliste.domain.group.GroupRepository;
import de.dhbw.ase.wgEinkaufsliste.domain.group.values.GroupId;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingList;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListItem;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListRepository;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListId;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListItemId;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ShoppingListService {

    private final ShoppingListRepository shoppingListRepository;
    private final GroupRepository groupRepository;

    public ShoppingListService(ShoppingListRepository shoppingListRepository, GroupRepository groupRepository) {
        this.shoppingListRepository = shoppingListRepository;
        this.groupRepository = groupRepository;
    }

    public Optional<ShoppingList> getById(ShoppingListId id) {
        return shoppingListRepository.findById(id);
    }

    public List<ShoppingList> getAll(Group group) {
        var result = new ArrayList<ShoppingList>();

        for (var listId : group.getListIds()) {
            var list = shoppingListRepository.findById(listId);
            list.ifPresent(result::add);
        }

        return result;
    }

    public List<ShoppingList> getAll(GroupId groupId) throws GroupNotFoundException {
        var group = groupRepository.findById(groupId);

        if (group.isEmpty()) {
            throw new GroupNotFoundException(groupId);
        }

        return getAll(group.get());
    }

    public ShoppingList create(Group group, String name) {
        var list = new ShoppingList(group, name);

        shoppingListRepository.save(list);
        groupRepository.save(group);

        return list;
    }

    public ShoppingList create(GroupId groupId, String name) throws GroupNotFoundException {
        var group = groupRepository.findById(groupId);

        if (group.isEmpty()) {
            throw new GroupNotFoundException(groupId);
        }

        return create(group.get(), name);
    }

    public void delete(ShoppingList list) {
        var group = groupRepository.findById(list.getGroupId());

        if (group.isPresent()) {
            group.get().removeList(list);
            groupRepository.save(group.get());
        }

        shoppingListRepository.deleteById(list.getId());
    }

    public void delete(ShoppingListId id) throws ShoppingListNotFoundException {
        var list = shoppingListRepository.findById(id);

        if (list.isEmpty()) {
            throw new ShoppingListNotFoundException(id);
        }

        delete(list.get());
    }

    public ShoppingList changeName(ShoppingList list, String newName) {
        list.setName(newName);
        return shoppingListRepository.save(list);
    }

    public ShoppingList changeName(ShoppingListId id, String newName) throws ShoppingListNotFoundException {
        var list = shoppingListRepository.findById(id);

        if (list.isEmpty()) {
            throw new ShoppingListNotFoundException(id);
        }

        return changeName(list.get(), newName);
    }

    public ShoppingList addOrUpdate(ShoppingList list, ShoppingListItem item) {
        list.addOrUpdateItem(item);
        return shoppingListRepository.save(list);
    }

    public ShoppingList addOrUpdate(ShoppingListId id, ShoppingListItem item) throws ShoppingListNotFoundException {
        var listOptional = shoppingListRepository.findById(id);

        if (listOptional.isEmpty()) {
            throw new ShoppingListNotFoundException(id);
        }

        return addOrUpdate(listOptional.get(), item);
    }

    public ShoppingList deleteItem(ShoppingList list, ShoppingListItemId itemId) {
        list.removeItemById(itemId);
        return shoppingListRepository.save(list);
    }

    public ShoppingList deleteItem(ShoppingListId id, ShoppingListItemId itemId) throws ShoppingListNotFoundException {
        var list = shoppingListRepository.findById(id);

        if (list.isEmpty()) {
            throw new ShoppingListNotFoundException(id);
        }

        return deleteItem(list.get(), itemId);
    }
}
