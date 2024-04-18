package de.dhbw.ase.wgEinkaufsliste.adapters.representations.group.resource;

import java.util.List;

public record GroupResource(String id, String name, List<GroupResourceUser> users, List<GroupResourceList> shoppingLists) {
    public record GroupResourceUser(String id, String name) { }
    public record GroupResourceList(String id, String name) { }
}
