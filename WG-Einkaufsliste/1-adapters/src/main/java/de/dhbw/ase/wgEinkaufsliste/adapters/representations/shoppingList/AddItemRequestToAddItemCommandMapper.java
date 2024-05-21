package de.dhbw.ase.wgEinkaufsliste.adapters.representations.shoppingList;

import de.dhbw.ase.wgEinkaufsliste.adapters.representations.shoppingList.request.AddShoppingListItemRequest;
import de.dhbw.ase.wgEinkaufsliste.application.shoppingList.command.AddShoppingListItemCommand;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.Quantity;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListId;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AddItemRequestToAddItemCommandMapper implements Function<Pair<ShoppingListId, AddShoppingListItemRequest>, AddShoppingListItemCommand> {

    @Override
    public AddShoppingListItemCommand apply(Pair<ShoppingListId, AddShoppingListItemRequest> pair) {
        return map(pair.getFirst(), pair.getSecond());
    }

    private AddShoppingListItemCommand map(ShoppingListId listId, AddShoppingListItemRequest request) {
        var quantity = new Quantity(request.quantity());
        return new AddShoppingListItemCommand(listId, request.name(), quantity, request.remarks());
    }
}
