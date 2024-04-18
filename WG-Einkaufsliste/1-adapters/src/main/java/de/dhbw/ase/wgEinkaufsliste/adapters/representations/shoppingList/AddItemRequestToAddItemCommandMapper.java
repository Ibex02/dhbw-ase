package de.dhbw.ase.wgEinkaufsliste.adapters.representations.shoppingList;

import de.dhbw.ase.wgEinkaufsliste.adapters.representations.shoppingList.request.AddShoppingListItemRequest;
import de.dhbw.ase.wgEinkaufsliste.application.shoppingList.command.AddShoppingListItemCommand;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class AddItemRequestToAddItemCommandMapper implements Function<AddShoppingListItemRequest, AddShoppingListItemCommand> {

    @Override
    public AddShoppingListItemCommand apply(AddShoppingListItemRequest request) {
        return map(request);
    }

    private AddShoppingListItemCommand map(AddShoppingListItemRequest request) {
        return new AddShoppingListItemCommand(request.name(), request.quantity());
    }
}
