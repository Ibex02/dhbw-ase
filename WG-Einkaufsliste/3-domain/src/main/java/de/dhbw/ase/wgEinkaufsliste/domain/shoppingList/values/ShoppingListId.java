package de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values;

import org.apache.commons.lang3.Validate;

import java.util.UUID;

public record ShoppingListId(String value) {
    public ShoppingListId {
        Validate.notEmpty(value);
    }

    public ShoppingListId() {
        this(UUID.randomUUID().toString());
    }
}
