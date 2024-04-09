package de.dhbw.ase.wgEinkaufsliste.adapters.persistence.shoppingLists;

import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingList;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class ShoppingListToShoppingListRecordMapper implements Function<ShoppingList, ShoppingListRecord> {

    @Override
    public ShoppingListRecord apply(ShoppingList list) {
        return map(list);
    }

    private ShoppingListRecord map(ShoppingList list) {
        return new ShoppingListRecord(list.getId(), list.getGroupId(), list.getName(), map(list.getItems()));
    }

    private List<ShoppingListRecord.ShoppingListRecordItem> map(List<ShoppingListItem> items) {
        return items.stream().map(this::map).toList();
    }

    private ShoppingListRecord.ShoppingListRecordItem map(ShoppingListItem item) {
        return new ShoppingListRecord.ShoppingListRecordItem(item.getId(), item.getName(), item.getAmount(), item.getPrice().getValue());
    }
}
