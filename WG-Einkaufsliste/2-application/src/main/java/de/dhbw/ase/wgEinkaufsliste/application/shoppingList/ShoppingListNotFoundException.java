package de.dhbw.ase.wgEinkaufsliste.application.shoppingList;

public class ShoppingListNotFoundException extends Exception {
    private final String listId;

    public ShoppingListNotFoundException(String listId) {
        super("No group for id: " + listId + " found");

        this.listId = listId;
    }

    public String getListId() {
        return listId;
    }
}
