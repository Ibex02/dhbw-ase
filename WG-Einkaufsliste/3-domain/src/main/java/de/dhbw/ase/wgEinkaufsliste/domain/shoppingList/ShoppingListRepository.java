package de.dhbw.ase.wgEinkaufsliste.domain.shoppingList;

import java.util.Optional;

public interface ShoppingListRepository {
    Optional<ShoppingList> findById(String id);
    ShoppingList save(ShoppingList list);
    void deleteById(String id);
}
