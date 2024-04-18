package de.dhbw.ase.wgEinkaufsliste.adapters.representations.shoppingList;

import de.dhbw.ase.wgEinkaufsliste.adapters.representations.shoppingList.resource.ShoppingListItemResource;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListItem;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ShoppingListItemToShoppingListItemResourceMapper implements Function<ShoppingListItem, ShoppingListItemResource> {

    @Override
    public ShoppingListItemResource apply(ShoppingListItem item) {
        return map(item);
    }

    private ShoppingListItemResource map(ShoppingListItem item) {
        var id = item.id().value();
        var quantity = item.quantity().value();

        return new ShoppingListItemResource(id, item.name(), quantity);
    }
}
