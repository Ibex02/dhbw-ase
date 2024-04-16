package de.dhbw.ase.wgEinkaufsliste.adapters.representations.shoppingList;

import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListItem;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.Price;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListItemId;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ShoppingListItemResourceToShoppingListItemMapper implements Function<ShoppingListItemResource, ShoppingListItem> {

    @Override
    public ShoppingListItem apply(ShoppingListItemResource item) {
        return map(item);
    }

    private ShoppingListItem map(ShoppingListItemResource item) {
        var id = new ShoppingListItemId();
        var price = new Price(item.price());

        return new ShoppingListItem(id, item.name(), item.amount(), price);
    }
}