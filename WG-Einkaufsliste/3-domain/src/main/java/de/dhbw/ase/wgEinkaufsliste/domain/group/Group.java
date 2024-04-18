package de.dhbw.ase.wgEinkaufsliste.domain.group;

import de.dhbw.ase.wgEinkaufsliste.domain.group.values.GroupId;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListId;
import de.dhbw.ase.wgEinkaufsliste.domain.user.values.UserId;
import org.apache.commons.lang3.Validate;

import java.util.*;

public class Group {

    private final GroupId id;
    private String name;

    private Set<UserId> usersIds = new HashSet<>();
    private Set<ShoppingListId> listIds = new HashSet<>();

    public Group(String name) {
        this.id = new GroupId();
        this.name = name;

        validate();
    }

    public Group(GroupId id, String name, Collection<UserId> usersIds, Collection<ShoppingListId> listIds) {
        this.id = id;
        this.name = name;
        this.usersIds = new HashSet<>(usersIds);
        this.listIds = new HashSet<>(listIds);

        validate();
    }

    public GroupId getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public Collection<ShoppingListId> getListIds() {
        return listIds;
    }
    public Collection<UserId> getUsersIds() {
        return usersIds;
    }

    public void setName(String name) {
        Validate.notBlank(name);
        this.name = name;
    }

    public void addUser(UserId id) {
        usersIds.add(id);
    }
    public void removeUser(UserId id) {
        usersIds.remove(id);
    }

    public void addList(ShoppingListId id) {
        listIds.add(id);
    }
    public void removeList(ShoppingListId id) {
        listIds.remove(id);
    }

    public boolean isEmpty() {
        return usersIds.isEmpty();
    }

    private void validate() {
        Objects.requireNonNull(id);
        Objects.requireNonNull(usersIds);
        Objects.requireNonNull(listIds);
        Validate.notBlank(name);
    }
}