package de.dhbw.ase.wgEinkaufsliste.domain.shoppingList;


import org.apache.commons.lang3.Validate;

public class ShoppingListItem {
    private String id;
    private String name;
    private int amount;
    private Price price;

    public ShoppingListItem(String id, String name, int amount, Price price) {
        Validate.notEmpty(id);
        Validate.notEmpty(name);

        this.id = id;
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    public String getId() {
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
