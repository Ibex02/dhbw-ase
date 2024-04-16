package de.dhbw.ase.wgEinkaufsliste.application.shoppingList;

import de.dhbw.ase.wgEinkaufsliste.application.group.GroupNotFoundException;
import de.dhbw.ase.wgEinkaufsliste.domain.group.Group;
import de.dhbw.ase.wgEinkaufsliste.domain.group.GroupRepository;
import de.dhbw.ase.wgEinkaufsliste.domain.group.values.GroupId;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingList;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListItem;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListRepository;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListId;
import org.springframework.stereotype.Service;

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

    public ShoppingList create(Group group, String name) {
        var list = new ShoppingList(group, name);

        shoppingListRepository.save(list);
        groupRepository.save(group);

        return list;
    }

    public ShoppingList createById(GroupId groupId, String name) throws GroupNotFoundException {
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

    public void deleteById(ShoppingListId id) throws ShoppingListNotFoundException {
        var shoppingList = shoppingListRepository.findById(id);

        if (shoppingList.isEmpty()) {
            throw new ShoppingListNotFoundException(id);
        }

        delete(shoppingList.get());
    }

    public ShoppingList changeName(ShoppingList list, String newName) {
        list.setName(newName);
        return shoppingListRepository.save(list);
    }

    public ShoppingList changeNameById(ShoppingListId id, String newName) throws ShoppingListNotFoundException {
        var shoppingList = shoppingListRepository.findById(id);

        if (shoppingList.isEmpty()) {
            throw new ShoppingListNotFoundException(id);
        }

        return changeName(shoppingList.get(), newName);
    }

    public ShoppingList addItem(ShoppingList list, ShoppingListItem item) {
        list.addItem(item);
        return shoppingListRepository.save(list);
    }

    public ShoppingList addItemById(ShoppingListId id, ShoppingListItem item) throws ShoppingListNotFoundException {
        var shoppingList = shoppingListRepository.findById(id);

        if (shoppingList.isEmpty()) {
            throw new ShoppingListNotFoundException(id);
        }

        return addItem(shoppingList.get(), item);
    }

    public ShoppingList deleteItem(ShoppingList list, String itemId) {
        list.removeItemById(itemId);
        return shoppingListRepository.save(list);
    }

    public ShoppingList deleteItemById(ShoppingListId id, String itemId) throws ShoppingListNotFoundException {
        var shoppingList = shoppingListRepository.findById(id);

        if (shoppingList.isEmpty()) {
            throw new ShoppingListNotFoundException(id);
        }

        return deleteItem(shoppingList.get(), itemId);
    }
}
