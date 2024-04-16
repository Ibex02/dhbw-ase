package de.dhbw.ase.wgEinkaufsliste.adapters.representations.shoppingList;

import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListItem;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.Price;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ShoppingListItemResourceToShoppingListItemMapper implements Function<ShoppingListItemResource, ShoppingListItem> {

    @Override
    public ShoppingListItem apply(ShoppingListItemResource item) {
        return map(item);
    }

    private ShoppingListItem map(ShoppingListItemResource item) {
        return new ShoppingListItem(item.id(), item.name(), item.amount(), new Price(item.price()));
    }
}
