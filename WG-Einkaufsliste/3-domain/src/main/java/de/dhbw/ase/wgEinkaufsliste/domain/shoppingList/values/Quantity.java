package de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values;

import org.apache.commons.lang3.Validate;

public record Quantity(int value) {
    public Quantity {
        Validate.inclusiveBetween(0, Integer.MAX_VALUE, value);
    }
}
