package de.dhbw.ase.wgEinkaufsliste.domain.user;

import org.apache.commons.lang3.Validate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class User {

    private final String id;
    private String email;
    private String passwordHash;
    private String name;
    private List<String> groupIds = new ArrayList<>();

    public User(String id, String email, String passwordHash, String name, List<String> groupIds) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.name = name;
        this.groupIds = groupIds;

        validate();
    }

    public User(String email, String passwordHash, String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.passwordHash = passwordHash;
        this.email = email;

        validate();
    }

    public String getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return passwordHash;
    }
    public String getName() {
        return name;
    }

    public List<String> getGroupIds() {
        return groupIds;
    }

    public void setName(String newName) {
        Validate.notBlank(newName);

        this.name = newName;
    }

    private void validate() {
        Validate.notBlank(id);
        Validate.notBlank(email);
        Validate.notBlank(name);
        Validate.notBlank(passwordHash);
    }
}
