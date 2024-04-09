package de.dhbw.ase.wgEinkaufsliste.adapters.representations.groups;

import java.util.ArrayList;
import java.util.List;

public class GroupResource {

    private String id;
    private String name;

    private List<GroupResourceUser> users = new ArrayList<>();
    private List<GroupResourceList> lists = new ArrayList<>();

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<GroupResourceUser> getUsers() {
        return users;
    }

    public List<GroupResourceList> getLists() {
        return lists;
    }
}


