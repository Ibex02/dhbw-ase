package de.dhbw.ase.wgEinkaufsliste.domain.shoppingList;

import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.Quantity;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListItemId;
import org.apache.commons.lang3.Validate;

import java.util.Objects;

public record ShoppingListItem(ShoppingListItemId id, String name, Quantity quantity) {
    public ShoppingListItem(String name, Quantity quantity) {
        this(new ShoppingListItemId(), name, quantity);
    }

    public ShoppingListItem {
        Objects.requireNonNull(id);
        Objects.requireNonNull(quantity);
        Validate.notEmpty(name);
    }
}
