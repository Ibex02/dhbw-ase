package de.dhbw.ase.wgEinkaufsliste.adapters.representations.shoppingList;

import java.util.List;

public record ShoppingListResource(String id, String name, List<ShoppingListItemResource> items) { }
