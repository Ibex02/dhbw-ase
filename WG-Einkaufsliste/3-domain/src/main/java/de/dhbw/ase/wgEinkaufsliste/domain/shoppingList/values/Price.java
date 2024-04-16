package de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values;

import org.apache.commons.lang3.Validate;

public record Price(double value) {

    public Price {
        Validate.inclusiveBetween(0, Integer.MAX_VALUE, value);
    }
}
