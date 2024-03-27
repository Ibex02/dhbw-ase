package de.dhbw.ase.wgEinkaufsliste.domain.entities;

import de.dhbw.ase.wgEinkaufsliste.domain.values.User;
import org.apache.commons.lang3.Validate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Group {

    private String id;

    private String name;

    public List<String> getUsersIds() {
        return usersIds;
    }

    public List<String> getListIds() {
        return listIds;
    }

    private List<String> usersIds = new ArrayList<>();
    private List<String> listIds = new ArrayList<>();

    public Group(String name) {
        Validate.notBlank(name);

        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public Group(String id, String name, List<String> usersIds, List<String> listIds) {
        Validate.notBlank(name);
        Validate.notBlank(id);
        Validate.notEmpty(usersIds);

        this.id = id;
        this.name = name;
        this.usersIds = usersIds;
        this.listIds = listIds;
    }

    public boolean addUser(User user) {
        return usersIds.add(user.getId());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
