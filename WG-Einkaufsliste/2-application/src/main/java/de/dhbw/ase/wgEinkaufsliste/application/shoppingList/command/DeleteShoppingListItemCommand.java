package de.dhbw.ase.wgEinkaufsliste.application.shoppingList.command;

import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListId;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListItemId;

public record DeleteShoppingListItemCommand(ShoppingListId listId, ShoppingListItemId itemId) { }