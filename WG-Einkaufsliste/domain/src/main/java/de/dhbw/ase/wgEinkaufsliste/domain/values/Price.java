package de.dhbw.ase.wgEinkaufsliste.domain.values;

import org.apache.commons.lang3.Validate;

public class Price {

    private int amount;

    public Price(int amount) {
        Validate.inclusiveBetween(0, Integer.MAX_VALUE, amount);

        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }
}
