package de.dhbw.ase.wgEinkaufsliste.domain.values;

import org.apache.commons.lang3.Validate;

import java.util.UUID;

public class User {
    private String name;
    private String email;
    private String passwordHash;

    private String id;

    public User(String name) {
        Validate.notBlank(name);

        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
