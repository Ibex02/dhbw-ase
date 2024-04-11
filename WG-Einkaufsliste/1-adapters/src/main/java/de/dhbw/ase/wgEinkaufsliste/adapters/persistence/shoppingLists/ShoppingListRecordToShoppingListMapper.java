package de.dhbw.ase.wgEinkaufsliste.adapters.persistence.shoppingLists;

import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingList;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListItem;
import de.dhbw.ase.wgEinkaufsliste.domain.values.Price;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class ShoppingListRecordToShoppingListMapper implements Function<ShoppingListRecord, ShoppingList> {

    @Override
    public ShoppingList apply(ShoppingListRecord record) {
        return map(record);
    }

    private ShoppingList map(ShoppingListRecord record) {
        return new ShoppingList(record.getId(), record.getGroupId(), record.getName(), map(record.getItems()));
    }

    private List<ShoppingListItem> map(List<ShoppingListRecord.ShoppingListRecordItem> items) {
        return items.stream().map(this::map).toList();
    }

    private ShoppingListItem map(ShoppingListRecord.ShoppingListRecordItem item) {
        return new ShoppingListItem(item.id(), item.name(), item.amount(), new Price(item.price()));
    }
}
