package de.dhbw.ase.wgEinkaufsliste.plugins.persistence.shoppingList;

import de.dhbw.ase.wgEinkaufsliste.adapters.persistence.shoppingLists.ShoppingListRecordToShoppingListMapper;
import de.dhbw.ase.wgEinkaufsliste.adapters.persistence.shoppingLists.ShoppingListToShoppingListRecordMapper;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingList;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ShoppingListRepositoryBridge implements ShoppingListRepository {

    private final ShoppingListToShoppingListRecordMapper mapToRecord;
    private final ShoppingListRecordToShoppingListMapper mapFromRecord;
    private final MongoShoppingListRepository repository;

    @Autowired
    public ShoppingListRepositoryBridge(
            ShoppingListToShoppingListRecordMapper mapToRecord,
            ShoppingListRecordToShoppingListMapper mapFromRecord,
            MongoShoppingListRepository repository) {
        this.mapToRecord = mapToRecord;
        this.mapFromRecord = mapFromRecord;
        this.repository = repository;
    }

    @Override
    public ShoppingList findById(String id) {
        var entity = repository.findById(id);
        return entity.map(mapFromRecord).orElse(null);
    }

    @Override
    public void save(ShoppingList list) {
        var entity = mapToRecord.apply(list);
        repository.save(entity);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
