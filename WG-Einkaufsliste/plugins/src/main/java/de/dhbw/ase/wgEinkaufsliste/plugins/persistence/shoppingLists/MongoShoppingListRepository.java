package de.dhbw.ase.wgEinkaufsliste.plugins.persistence.shoppingLists;

import de.dhbw.ase.wgEinkaufsliste.adapters.persistence.ShoppingListRecord;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoShoppingListRepository extends MongoRepository<ShoppingListRecord, String> { }
