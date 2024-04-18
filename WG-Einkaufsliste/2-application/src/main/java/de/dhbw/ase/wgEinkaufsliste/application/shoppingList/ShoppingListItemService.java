package de.dhbw.ase.wgEinkaufsliste.application.shoppingList;

import de.dhbw.ase.wgEinkaufsliste.application.shoppingList.command.AddShoppingListItemCommand;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingList;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListItem;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListNotFoundException;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListRepository;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.Quantity;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListId;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListItemId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingListItemService {

    private final ShoppingListRepository shoppingListRepository;

    @Autowired
    public ShoppingListItemService(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }

    public ShoppingList addItem(ShoppingList list, AddShoppingListItemCommand command) {
        var item = new ShoppingListItem(command.name(), new Quantity(command.quantity()));
        list.addOrUpdateItem(item);
        return shoppingListRepository.save(list);
    }

    public ShoppingList deleteItem(ShoppingList list, ShoppingListItemId itemId) {
        list.removeItem(itemId);
        return shoppingListRepository.save(list);
    }

    public ShoppingList addItem(ShoppingListId id, AddShoppingListItemCommand command) throws ShoppingListNotFoundException {
        var list = shoppingListRepository.getById(id);
        return addItem(list, command);
    }

    public ShoppingList deleteItem(ShoppingListId id, ShoppingListItemId itemId) throws ShoppingListNotFoundException {
        var list = shoppingListRepository.getById(id);
        return deleteItem(list, itemId);
    }
}
