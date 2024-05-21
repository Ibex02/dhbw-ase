package de.dhbw.ase.wgEinkaufsliste.adapters.persistence.shoppingLists;

import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingList;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListItem;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.Quantity;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ShoppingListToShoppingListRecordMapper implements Function<ShoppingList, ShoppingListRecord> {

    @Override
    public ShoppingListRecord apply(ShoppingList list) {
        return map(list);
    }

    private ShoppingListRecord map(ShoppingList list) {
        var id = list.getId().value();
        var groupId = list.getGroupId().value();
        var items = list.getItems().stream().map(this::map).toList();;

        return new ShoppingListRecord(id, groupId, list.getName(), items);
    }

    private ShoppingListRecord.ShoppingListRecordItem map(ShoppingListItem item) {
        var id = item.id().value();
        var quantity = item.quantity().value();

        return new ShoppingListRecord.ShoppingListRecordItem(id, item.name(), quantity, item.remarks());
    }
}
