package de.dhbw.ase.wgEinkaufsliste.adapters.representations.shoppingList;

import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingList;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListItem;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListId;
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
        var id = list.getId().value();
        var items = map(list.getItems());

        return new ShoppingListResource(id, list.getName(), items);
    }

    private List<ShoppingListItemResource> map(List<ShoppingListItem> items) {
        return items.stream().map(itemToResource).toList();
    }
}
