package de.dhbw.ase.wgEinkaufsliste.application.shoppingList;

import de.dhbw.ase.wgEinkaufsliste.application.shoppingList.command.AddShoppingListItemCommand;
import de.dhbw.ase.wgEinkaufsliste.application.shoppingList.command.DeleteShoppingListItemCommand;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingList;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListItem;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListNotFoundException;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListRepository;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.Quantity;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingListItemService {

    private final ShoppingListRepository shoppingListRepository;

    @Autowired
    public ShoppingListItemService(ShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }

    public ShoppingList addItem(ShoppingListId listId, String name, Quantity quantity, String remarks) throws ShoppingListNotFoundException {
        var list = shoppingListRepository.getById(listId);
        var item = new ShoppingListItem(name, quantity, remarks);

        list.addOrUpdateItem(item);
        return shoppingListRepository.save(list);
    }

    public ShoppingList deleteItem(DeleteShoppingListItemCommand command) throws ShoppingListNotFoundException {
        var list = shoppingListRepository.getById(command.listId());

        list.removeItem(command.itemId());
        return shoppingListRepository.save(list);
    }
}