package de.dhbw.ase.wgEinkaufsliste.adapters.representations.group;

public class GroupResourceList {

    private final String id;
    private final String name;

    public GroupResourceList(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
