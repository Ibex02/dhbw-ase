package de.dhbw.ase.wgEinkaufsliste.plugins.persistence.shoppingLists;

import de.dhbw.ase.wgEinkaufsliste.domain.repositories.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ShoppingListRepositoryBridge implements ShoppingListRepository {

    private final MongoShoppingListRepository shoppingListRepository;

    @Autowired
    public ShoppingListRepositoryBridge(MongoShoppingListRepository shoppingListRepository) {
        this.shoppingListRepository = shoppingListRepository;
    }
}
