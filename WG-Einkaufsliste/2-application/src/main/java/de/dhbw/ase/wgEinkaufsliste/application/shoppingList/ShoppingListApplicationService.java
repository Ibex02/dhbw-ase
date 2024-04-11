package de.dhbw.ase.wgEinkaufsliste.application.shoppingList;

import de.dhbw.ase.wgEinkaufsliste.domain.group.Group;
import de.dhbw.ase.wgEinkaufsliste.domain.group.GroupRepository;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingList;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListItem;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListRepository;
import org.springframework.stereotype.Service;

@Service
public class ShoppingListApplicationService {

    private final ShoppingListRepository shoppingListRepository;
    private final GroupRepository groupRepository;

    public ShoppingListApplicationService(ShoppingListRepository shoppingListRepository, GroupRepository groupRepository) {
        this.shoppingListRepository = shoppingListRepository;
        this.groupRepository = groupRepository;
    }


    public ShoppingList create(Group group, String name) {
        var list = new ShoppingList(group, name);

        shoppingListRepository.save(list);
        groupRepository.save(group);

        return list;
    }

    public void delete(ShoppingList list) {
        var group = groupRepository.findById(list.getGroupId());

        group.removeList(list);

        groupRepository.save(group);
        shoppingListRepository.deleteById(list.getId());
    }

    public void changeName(ShoppingList list, String name) {
        list.setName(name);
        shoppingListRepository.save(list);
    }

    public void addItem(ShoppingList list, ShoppingListItem item) {
        list.addItem(item);

        shoppingListRepository.save(list);
    }

    public void deleteItemById(ShoppingList list, String itemId) {
        list.removeItemById(itemId);
        shoppingListRepository.save(list);
    }
}
