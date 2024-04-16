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

    public List<ShoppingList> getAll(GroupId groupId) throws GroupNotFoundException {
        var groupOpt = groupRepository.findById(groupId);

        if (groupOpt.isEmpty()) throw new GroupNotFoundException(groupId);
        var group = groupOpt.get();

        var result = new ArrayList<ShoppingList>();
        for (var listId : group.getListIds()) {
            var list = shoppingListRepository.findById(listId);
            list.ifPresent(result::add);
        }

        return result;
    }

    public ShoppingList create(GroupId groupId, String name) throws GroupNotFoundException {
        var groupOpt = groupRepository.findById(groupId);

        if (groupOpt.isEmpty()) throw new GroupNotFoundException(groupId);
        var group = groupOpt.get();

        var list = new ShoppingList(group, name);

        shoppingListRepository.save(list);
        groupRepository.save(group);

        return list;
    }

    public void delete(ShoppingListId id) throws ShoppingListNotFoundException {
        var listOpt = shoppingListRepository.findById(id);

        if (listOpt.isEmpty()) throw new ShoppingListNotFoundException(id);
        var list = listOpt.get();

        var groupOpt = groupRepository.findById(list.getGroupId());
        if (groupOpt.isPresent()) {
            var group = groupOpt.get();

            group.removeList(list);
            groupRepository.save(group);
        }

        shoppingListRepository.deleteById(list.getId());
    }

    public ShoppingList changeName(ShoppingListId id, String newName) throws ShoppingListNotFoundException {
        var listOpt = shoppingListRepository.findById(id);

        if (listOpt.isEmpty()) throw new ShoppingListNotFoundException(id);
        var list = listOpt.get();

        list.setName(newName);
        return shoppingListRepository.save(list);
    }

    public ShoppingList addOrUpdate(ShoppingListId id, ShoppingListItem item) throws ShoppingListNotFoundException {
        var listOpt = shoppingListRepository.findById(id);

        if (listOpt.isEmpty()) throw new ShoppingListNotFoundException(id);
        var list = listOpt.get();

        list.addOrUpdateItem(item);
        return shoppingListRepository.save(list);
    }

    public ShoppingList deleteItem(ShoppingListId id, ShoppingListItemId itemId) throws ShoppingListNotFoundException {
        var listOpt = shoppingListRepository.findById(id);

        if (listOpt.isEmpty()) throw new ShoppingListNotFoundException(id);
        var list = listOpt.get();

        list.removeItemById(itemId);
        return shoppingListRepository.save(list);
    }
}
