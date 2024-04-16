package de.dhbw.ase.wgEinkaufsliste.domain.shoppingList;


import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.Price;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListItemId;
import org.apache.commons.lang3.Validate;

public class ShoppingListItem {
    private ShoppingListItemId id;
    private String name;
    private int amount;
    private Price price;

    public ShoppingListItem(ShoppingListItemId id, String name, int amount, Price price) {
        Validate.notNull(id, "");
        Validate.notEmpty(name);

        this.id = id;
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    public ShoppingListItemId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public Price getPrice() {
        return price;
    }
}
