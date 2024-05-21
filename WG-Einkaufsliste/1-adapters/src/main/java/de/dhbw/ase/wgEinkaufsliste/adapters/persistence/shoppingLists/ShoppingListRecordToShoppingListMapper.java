package de.dhbw.ase.wgEinkaufsliste.adapters.persistence.shoppingLists;

import de.dhbw.ase.wgEinkaufsliste.domain.group.values.GroupId;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingList;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListItem;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.Quantity;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListId;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListItemId;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ShoppingListRecordToShoppingListMapper implements Function<ShoppingListRecord, ShoppingList> {

    @Override
    public ShoppingList apply(ShoppingListRecord record) {
        return map(record);
    }

    private ShoppingList map(ShoppingListRecord record) {
        var id = new ShoppingListId(record.id());
        var groupId = new GroupId(record.groupId());
        var items = record.items().stream().map(this::map).toList();

        return new ShoppingList(id, groupId, record.name(), items);
    }

    private ShoppingListItem map(ShoppingListRecord.ShoppingListRecordItem item) {
        var id = new ShoppingListItemId(item.id());
        var quantity = new Quantity(item.quantity());

        return new ShoppingListItem(id, item.name(), quantity, item.remarks());
    }
}
