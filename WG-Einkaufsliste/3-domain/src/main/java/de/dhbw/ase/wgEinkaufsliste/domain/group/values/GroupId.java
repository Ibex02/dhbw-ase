package de.dhbw.ase.wgEinkaufsliste.domain.group.values;

import org.apache.commons.lang3.Validate;

import java.util.UUID;

public record GroupId(String value) {
    public GroupId {
        Validate.notEmpty(value);
    }

    public GroupId() {
        this(UUID.randomUUID().toString());
    }
}
