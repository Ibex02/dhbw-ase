package de.dhbw.ase.wgEinkaufsliste.application.shoppingList;

import de.dhbw.ase.wgEinkaufsliste.domain.group.GroupNotFoundException;
import de.dhbw.ase.wgEinkaufsliste.domain.group.values.GroupId;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingList;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListItem;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListNotFoundException;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListId;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListItemId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShoppingListService {

    private final ShoppingListManagementService managementService;
    private final ShoppingListItemService itemService;

    @Autowired
    public ShoppingListService(ShoppingListManagementService managementService, ShoppingListItemService itemService) {
        this.managementService = managementService;
        this.itemService = itemService;
    }

    public Optional<ShoppingList> findById(ShoppingListId id) {
        return managementService.findById(id);
    }

    public List<ShoppingList> getAll(GroupId groupId) throws GroupNotFoundException {
        return managementService.getAllForGroup(groupId);
    }

    public ShoppingList create(GroupId groupId, String name) throws GroupNotFoundException {
        return managementService.create(groupId, name);
    }
}
