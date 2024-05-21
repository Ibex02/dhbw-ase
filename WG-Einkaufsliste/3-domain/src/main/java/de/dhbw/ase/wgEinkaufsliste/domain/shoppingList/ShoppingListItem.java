package de.dhbw.ase.wgEinkaufsliste.domain.shoppingList;

import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.*;
import org.apache.commons.lang3.Validate;

import java.util.Objects;

public record ShoppingListItem(ShoppingListItemId id, String name, Quantity quantity, String remarks) {
    public ShoppingListItem(String name, Quantity quantity, String remarks) {
        this(new ShoppingListItemId(), name, quantity, remarks);
    }

    public ShoppingListItem {
        Objects.requireNonNull(id);
        Objects.requireNonNull(quantity);
        Validate.notEmpty(name);
    }
}
