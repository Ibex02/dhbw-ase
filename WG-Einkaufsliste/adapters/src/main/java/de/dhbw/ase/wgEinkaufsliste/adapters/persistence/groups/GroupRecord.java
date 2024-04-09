package de.dhbw.ase.wgEinkaufsliste.adapters.persistence.groups;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("Groups")
public class GroupRecord {

    @Id
    private String id;
    private String name;

    private List<String> users = new ArrayList<>();
    private List<String> lists = new ArrayList<>();

    public GroupRecord(String id, String name, List<String> users, List<String> lists) {
        this.id = id;
        this.name = name;
        this.users = users;
        this.lists = lists;
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

    public List<String> getLists() {
        return lists;
    }
}
