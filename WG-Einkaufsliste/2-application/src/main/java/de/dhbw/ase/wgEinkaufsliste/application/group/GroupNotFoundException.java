package de.dhbw.ase.wgEinkaufsliste.application.group;

import de.dhbw.ase.wgEinkaufsliste.domain.group.values.GroupId;

public class GroupNotFoundException extends Exception {

    private final GroupId id;

    public GroupNotFoundException(GroupId id) {
        super("No group for value: " + id + " found");

        this.id = id;
    }

    public GroupId getId() {
        return id;
    }
}
