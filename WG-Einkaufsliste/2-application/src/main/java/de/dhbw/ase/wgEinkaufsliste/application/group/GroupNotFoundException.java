package de.dhbw.ase.wgEinkaufsliste.application.group;

public class GroupNotFoundException extends Exception {

    private final String groupId;

    public GroupNotFoundException(String groupId) {
        super("No group for id: " + groupId + " found");

        this.groupId = groupId;
    }

    public String getGroupId() {
        return groupId;
    }
}
