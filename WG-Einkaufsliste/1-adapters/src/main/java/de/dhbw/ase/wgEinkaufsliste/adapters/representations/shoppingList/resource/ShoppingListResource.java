package de.dhbw.ase.wgEinkaufsliste.adapters.representations.shoppingList.resource;

import de.dhbw.ase.wgEinkaufsliste.adapters.representations.shoppingList.resource.ShoppingListItemResource;

import java.util.List;

public record ShoppingListResource(String id, String name, List<ShoppingListItemResource> items) { }
