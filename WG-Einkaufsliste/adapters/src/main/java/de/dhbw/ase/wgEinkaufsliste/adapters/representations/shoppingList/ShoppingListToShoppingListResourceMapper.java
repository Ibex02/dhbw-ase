package de.dhbw.ase.wgEinkaufsliste.adapters.representations.shoppingList;

import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingList;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class ShoppingListToShoppingListResourceMapper implements Function<ShoppingList, ShoppingListResource> {

    private final ShoppingListItemToShoppingListItemResourceMapper itemToResource;

    @Autowired
    public ShoppingListToShoppingListResourceMapper(ShoppingListItemToShoppingListItemResourceMapper itemToResource) {
        this.itemToResource = itemToResource;
    }

    @Override
    public ShoppingListResource apply(ShoppingList list) {
        return map(list);
    }

    private ShoppingListResource map(ShoppingList list) {
        return new ShoppingListResource(list.getId(), list.getName(), map(list.getItems()));
    }

    private List<ShoppingListItemResource> map(List<ShoppingListItem> items) {
        return items.stream().map(itemToResource).toList();
    }
}
