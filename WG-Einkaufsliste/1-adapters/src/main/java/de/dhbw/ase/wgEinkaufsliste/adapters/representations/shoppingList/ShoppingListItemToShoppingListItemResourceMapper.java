package de.dhbw.ase.wgEinkaufsliste.adapters.representations.shoppingList;

import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListItem;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListItemId;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ShoppingListItemToShoppingListItemResourceMapper implements Function<ShoppingListItem, ShoppingListItemResource> {

    @Override
    public ShoppingListItemResource apply(ShoppingListItem item) {
        return map(item);
    }

    private ShoppingListItemResource map(ShoppingListItem item) {
        var id = item.getId().value();
        var price = item.getPrice().value();

        return new ShoppingListItemResource(id, item.getName(), item.getAmount(), price);
    }
}
