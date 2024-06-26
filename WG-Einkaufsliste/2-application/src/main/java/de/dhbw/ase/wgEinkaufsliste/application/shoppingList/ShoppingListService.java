package de.dhbw.ase.wgEinkaufsliste.application.shoppingList;

import de.dhbw.ase.wgEinkaufsliste.domain.group.GroupNotFoundException;
import de.dhbw.ase.wgEinkaufsliste.domain.group.GroupRepository;
import de.dhbw.ase.wgEinkaufsliste.domain.group.values.GroupId;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingList;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListNotFoundException;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListRepository;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListId;
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

    public Optional<ShoppingList> findById(ShoppingListId id) {
        return shoppingListRepository.findById(id);
    }

    public List<ShoppingList> getAllForGroup(GroupId groupId) throws GroupNotFoundException {
        var group = groupRepository.getById(groupId);

        var result = new ArrayList<ShoppingList>();
        for (var listId : group.getListIds()) {
            var listOpt = shoppingListRepository.findById(listId);
            listOpt.ifPresent(result::add);
        }

        return result;
    }

    public ShoppingList create(GroupId groupId, String name) throws GroupNotFoundException {
        var group = groupRepository.getById(groupId);
        var list = new ShoppingList(group, name);

        shoppingListRepository.save(list);
        groupRepository.save(group);

        return list;
    }

    public void delete(ShoppingListId id) throws ShoppingListNotFoundException {
        var list = shoppingListRepository.getById(id);

        var groupOpt = groupRepository.findById(list.getGroupId());
        if (groupOpt.isPresent()) {
            var group = groupOpt.get();

            group.removeList(list.getId());
            groupRepository.save(group);
        }

        shoppingListRepository.deleteById(list.getId());
    }

    public ShoppingList changeName(ShoppingListId id, String newName) throws ShoppingListNotFoundException {
        var list = shoppingListRepository.getById(id);

        list.setName(newName);
        return shoppingListRepository.save(list);
    }
}
