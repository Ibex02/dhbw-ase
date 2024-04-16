package de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values;

import org.apache.commons.lang3.Validate;

import java.util.UUID;

public record ShoppingListItemId(String value) {
    public ShoppingListItemId {
        Validate.notEmpty(value);
    }
    public ShoppingListItemId() {
        this(UUID.randomUUID().toString());
    }
}
