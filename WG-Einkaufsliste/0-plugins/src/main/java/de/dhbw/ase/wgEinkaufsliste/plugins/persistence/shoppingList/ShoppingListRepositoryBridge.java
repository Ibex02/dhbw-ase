package de.dhbw.ase.wgEinkaufsliste.plugins.persistence.shoppingList;

import de.dhbw.ase.wgEinkaufsliste.adapters.persistence.shoppingLists.ShoppingListRecordToShoppingListMapper;
import de.dhbw.ase.wgEinkaufsliste.adapters.persistence.shoppingLists.ShoppingListToShoppingListRecordMapper;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingList;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListNotFoundException;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListRepository;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListId;
import de.dhbw.ase.wgEinkaufsliste.domain.user.UserNotFoundException;
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
    public Optional<ShoppingList> findById(ShoppingListId id) {
        var record = repository.findById(id.value());
        return record.map(mapFromRecord);
    }

    @Override
    public ShoppingList getById(ShoppingListId id) throws ShoppingListNotFoundException {
        return findById(id).orElseThrow(() -> new ShoppingListNotFoundException(id));
    }

    @Override
    public ShoppingList save(ShoppingList list) {
        var record = mapToRecord.apply(list);
        var saved = repository.save(record);

        return mapFromRecord.apply(saved);
    }

    @Override
    public void deleteById(ShoppingListId id) {
        repository.deleteById(id.value());
    }
}
