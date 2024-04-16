package de.dhbw.ase.wgEinkaufsliste.plugins.persistence.shoppingList;

import de.dhbw.ase.wgEinkaufsliste.adapters.persistence.shoppingLists.ShoppingListRecordToShoppingListMapper;
import de.dhbw.ase.wgEinkaufsliste.adapters.persistence.shoppingLists.ShoppingListToShoppingListRecordMapper;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingList;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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
    public Optional<ShoppingList> findById(String id) {
        var record = repository.findById(id);
        return record.map(mapFromRecord);
    }

    @Override
    public ShoppingList save(ShoppingList list) {
        var record = mapToRecord.apply(list);
        var saved = repository.save(record);

        return mapFromRecord.apply(saved);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
