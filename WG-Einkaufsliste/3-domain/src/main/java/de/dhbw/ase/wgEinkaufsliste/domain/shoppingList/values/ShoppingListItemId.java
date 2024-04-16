package de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values;

import org.apache.commons.lang3.Validate;

import java.util.UUID;

public record ShoppingListItemId(String id) {
    public ShoppingListItemId {
        Validate.notEmpty(id);
    }
    public ShoppingListItemId() {
        this(UUID.randomUUID().toString());
    }
}
