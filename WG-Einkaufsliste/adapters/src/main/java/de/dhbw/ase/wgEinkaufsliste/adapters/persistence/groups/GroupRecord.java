package de.dhbw.ase.wgEinkaufsliste.adapters.persistence.groups;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("group")
public class GroupRecord {

    @Id
    private final String id;
    private final String name;

    private final List<String> users;
    private final List<String> shoppingLists;

    public GroupRecord(String id, String name, List<String> users, List<String> shoppingLists) {
        this.id = id;
        this.name = name;
        this.users = users;
        this.shoppingLists = shoppingLists;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getUsers() {
        return users;
    }

    public List<String> getShoppingLists() {
        return shoppingLists;
    }
}
