package de.dhbw.ase.wgEinkaufsliste.adapters.persistence;

import jakarta.persistence.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("Groups")
public class GroupEntity {

    @Id
    private String id;

    private String name;
    private List<String> usersIds = new ArrayList<>();
    private List<String> listIds = new ArrayList<>();

    protected GroupEntity() { }

    public GroupEntity(String id, String name, List<String> usersIds, List<String> listIds) {
        this.id = id;
        this.name = name;
        this.usersIds = usersIds;
        this.listIds = listIds;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<String> getUsersIds() {
        return usersIds;
    }

    public List<String> getListIds() {
        return listIds;
    }
}
