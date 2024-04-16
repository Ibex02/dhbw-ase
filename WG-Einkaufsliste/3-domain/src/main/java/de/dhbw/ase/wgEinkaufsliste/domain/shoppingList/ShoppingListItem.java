package de.dhbw.ase.wgEinkaufsliste.domain.shoppingList;

import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.Quantity;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.Price;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListItemId;
import org.apache.commons.lang3.Validate;

public record ShoppingListItem(ShoppingListItemId id, String name, Quantity quantity, Price price) {
    public ShoppingListItem {
        Validate.notNull(id, "");
        Validate.notEmpty(name);
        Validate.notNull(price, "");
        Validate.notNull(quantity, "");
    }
}
