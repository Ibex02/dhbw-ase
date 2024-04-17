package de.dhbw.ase.wgEinkaufsliste.application.shoppingList;

import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingList;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListItem;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListNotFoundException;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListRepository;
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

    public ShoppingList addOrUpdate(ShoppingListId id, ShoppingListItem item) throws ShoppingListNotFoundException {
        var list = getById(id);

        list.addOrUpdateItem(item);
        return shoppingListRepository.save(list);
    }

    public ShoppingList deleteItem(ShoppingListId id, ShoppingListItemId itemId) throws ShoppingListNotFoundException {
        var list = getById(id);

        list.removeItemById(itemId);
        return shoppingListRepository.save(list);
    }

    public ShoppingList getById(ShoppingListId id) throws ShoppingListNotFoundException {
        var listOpt = shoppingListRepository.findById(id);

        if (listOpt.isEmpty()) throw new ShoppingListNotFoundException(id);
        return listOpt.get();
    }
}
