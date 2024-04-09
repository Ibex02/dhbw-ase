package de.dhbw.ase.wgEinkaufsliste.domain.shoppingList;

public interface ShoppingListRepository {
    ShoppingList findById(String id);
    void save(ShoppingList list);
    void deleteById(String id);
}
