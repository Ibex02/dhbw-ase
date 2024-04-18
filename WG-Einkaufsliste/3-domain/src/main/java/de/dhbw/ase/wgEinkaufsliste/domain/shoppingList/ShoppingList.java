package de.dhbw.ase.wgEinkaufsliste.domain.shoppingList;

import de.dhbw.ase.wgEinkaufsliste.domain.group.Group;
import de.dhbw.ase.wgEinkaufsliste.domain.group.values.GroupId;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListId;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListItemId;
import org.apache.commons.lang3.Validate;

import java.util.*;
import java.util.stream.Collectors;

public class ShoppingList {
    private final ShoppingListId id;
    private final GroupId groupId;
    private String name;
    private Map<ShoppingListItemId, ShoppingListItem> items = new HashMap<>();

    public ShoppingList(ShoppingListId id, GroupId groupId, String name, Collection<ShoppingListItem> items) {
        this.id = id;
        this.groupId = groupId;
        this.name = name;
        this.items = items.stream().collect(Collectors.toMap(ShoppingListItem::id, x -> x));

        validate();
    }

    public ShoppingList(Group group, String name) {

        this.id = new ShoppingListId();
        this.name = name;

        group.addList(getId());
        this.groupId = group.getId();

        validate();
    }

    public ShoppingListId getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        Validate.notBlank(name);
        this.name = name;
    }

    public void addOrUpdateItem(ShoppingListItem item) {
        items.put(item.id(), item);
    }

    public void removeItem(ShoppingListItemId id) {
        items.remove(id);
    }

    public GroupId getGroupId() {
        return groupId;
    }

    public Collection<ShoppingListItem> getItems() {
        return items.values();
    }

    private void validate() {
        Objects.requireNonNull(id);
        Objects.requireNonNull(groupId);
        Validate.notBlank(name);
    }
}
