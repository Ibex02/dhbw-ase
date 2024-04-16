package de.dhbw.ase.wgEinkaufsliste.domain.shoppingList;

import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListId;

import java.util.Optional;

public interface ShoppingListRepository {
    Optional<ShoppingList> findById(ShoppingListId id);
    ShoppingList save(ShoppingList list);
    void deleteById(ShoppingListId id);
}
