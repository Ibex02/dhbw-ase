package de.dhbw.ase.wgEinkaufsliste.plugins.persistence.shoppingList;

import de.dhbw.ase.wgEinkaufsliste.adapters.persistence.shoppingLists.ShoppingListRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoShoppingListRepository extends MongoRepository<ShoppingListRecord, String> { }
