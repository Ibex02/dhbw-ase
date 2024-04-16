package de.dhbw.ase.wgEinkaufsliste.domain.user.values;

import org.apache.commons.lang3.Validate;

import java.util.UUID;

public record UserId(String value) {
    public UserId {
        Validate.notEmpty(value);
    }

    public UserId() {
        this(UUID.randomUUID().toString());
    }
}
