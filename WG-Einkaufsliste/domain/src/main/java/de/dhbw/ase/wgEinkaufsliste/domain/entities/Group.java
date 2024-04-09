package de.dhbw.ase.wgEinkaufsliste.domain.entities;

import org.apache.commons.lang3.Validate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Group {

    private String id;
    private String name;

    private List<String> usersIds = new ArrayList<>();
    private List<String> listIds = new ArrayList<>();

    public Group(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public Group(String id, String name, List<String> usersIds, List<String> listIds) {
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

    public boolean addUser(User user) {
        user.getGroupIds().remove(id);
        return usersIds.add(user.getId());
    }

    private void validate() {
        Validate.notBlank(name);
        Validate.notBlank(id);
        Validate.notEmpty(usersIds);
    }
}
