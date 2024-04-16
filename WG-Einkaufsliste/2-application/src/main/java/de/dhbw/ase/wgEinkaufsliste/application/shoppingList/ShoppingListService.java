package de.dhbw.ase.wgEinkaufsliste.application.shoppingList;

import de.dhbw.ase.wgEinkaufsliste.application.group.GroupNotFoundException;
import de.dhbw.ase.wgEinkaufsliste.domain.group.Group;
import de.dhbw.ase.wgEinkaufsliste.domain.group.GroupRepository;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingList;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListItem;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListRepository;
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

    public Optional<ShoppingList> getById(String id) {
        return shoppingListRepository.findById(id);
    }

    public ShoppingList create(Group group, String name) {
        var list = new ShoppingList(group, name);

        shoppingListRepository.save(list);
        groupRepository.save(group);

        return list;
    }

    public ShoppingList createById(String groupId, String name) throws GroupNotFoundException {
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

    public void deleteById(String shoppingListId) throws ShoppingListNotFoundException {
        var shoppingList = shoppingListRepository.findById(shoppingListId);

        if (shoppingList.isEmpty()) {
            throw new ShoppingListNotFoundException(shoppingListId);
        }

        delete(shoppingList.get());
    }

    public ShoppingList changeName(ShoppingList list, String newName) {
        list.setName(newName);
        return shoppingListRepository.save(list);
    }

    public ShoppingList changeNameById(String shoppingListId, String newName) throws ShoppingListNotFoundException {
        var shoppingList = shoppingListRepository.findById(shoppingListId);

        if (shoppingList.isEmpty()) {
            throw new ShoppingListNotFoundException(shoppingListId);
        }

        return changeName(shoppingList.get(), newName);
    }

    public ShoppingList addItem(ShoppingList list, ShoppingListItem item) {
        list.addItem(item);
        return shoppingListRepository.save(list);
    }

    public ShoppingList addItemById(String shoppingListId, ShoppingListItem item) throws ShoppingListNotFoundException {
        var shoppingList = shoppingListRepository.findById(shoppingListId);

        if (shoppingList.isEmpty()) {
            throw new ShoppingListNotFoundException(shoppingListId);
        }

        return addItem(shoppingList.get(), item);
    }

    public ShoppingList deleteItem(ShoppingList list, String itemId) {
        list.removeItemById(itemId);
        return shoppingListRepository.save(list);
    }

    public ShoppingList deleteItemById(String shoppingListId, String itemId) throws ShoppingListNotFoundException {
        var shoppingList = shoppingListRepository.findById(shoppingListId);

        if (shoppingList.isEmpty()) {
            throw new ShoppingListNotFoundException(shoppingListId);
        }

        return deleteItem(shoppingList.get(), itemId);
    }
}
