package de.dhbw.ase.wgEinkaufsliste.domain.shoppingList;


import de.dhbw.ase.wgEinkaufsliste.domain.values.Price;
import org.apache.commons.lang3.Validate;

import java.util.UUID;

public class ShoppingListItem {
    private String id;

    private String name;

    private int amount;

    private Price price;

    private String remarks;

    public ShoppingListItem(String id, String name, int amount, Price price, String remarks) {
        Validate.notEmpty(id);
        Validate.notEmpty(name);

        this.id = id;
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.remarks = remarks;
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

    public String getRemarks() {
        return remarks;
    }
}
