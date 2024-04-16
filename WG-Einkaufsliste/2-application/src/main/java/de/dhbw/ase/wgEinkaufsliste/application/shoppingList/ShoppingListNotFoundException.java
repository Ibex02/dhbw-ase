package de.dhbw.ase.wgEinkaufsliste.application.shoppingList;

import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListId;

public class ShoppingListNotFoundException extends Exception {
    private final ShoppingListId id;

    public ShoppingListNotFoundException(ShoppingListId id) {
        super("No list for value: " + id + " found");

        this.id = id;
    }

    public ShoppingListId getId() {
        return id;
    }
}
