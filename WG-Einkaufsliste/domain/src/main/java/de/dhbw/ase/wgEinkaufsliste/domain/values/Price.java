package de.dhbw.ase.wgEinkaufsliste.domain.values;

import org.apache.commons.lang3.Validate;

public class Price {

    private double value;

    public Price(double value) {
        Validate.inclusiveBetween(0, Integer.MAX_VALUE, value);

        this.value = value;
    }

    public double getValue() {
        return value;
    }
}
