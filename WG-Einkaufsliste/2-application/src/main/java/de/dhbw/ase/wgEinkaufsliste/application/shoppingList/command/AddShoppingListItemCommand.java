package de.dhbw.ase.wgEinkaufsliste.application.shoppingList.command;

import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListId;

public record AddShoppingListItemCommand(ShoppingListId listId, String name, int quantity) { }