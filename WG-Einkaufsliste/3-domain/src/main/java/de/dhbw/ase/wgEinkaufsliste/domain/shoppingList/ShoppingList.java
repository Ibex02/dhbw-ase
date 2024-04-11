package de.dhbw.ase.wgEinkaufsliste.domain.shoppingList;

import de.dhbw.ase.wgEinkaufsliste.domain.group.Group;
import org.apache.commons.lang3.Validate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class ShoppingList {
    private final String id;
    private final String groupId;
    private String name;
    private List<ShoppingListItem> items = new ArrayList<>();

    public ShoppingList(String id, String groupId, String name, List<ShoppingListItem> items) {
        this.id = id;
        this.groupId = groupId;
        this.name = name;
        this.items = items;

        validate();
    }

    public ShoppingList(Group group, String name) {

        this.id = UUID.randomUUID().toString();
        this.name = name;

        group.addList(this);
        this.groupId = group.getId();

        validate();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        Validate.notBlank(name);
        this.name = name;
    }

    public void addItem(ShoppingListItem item) {
        items.add(item);
    }

    public void removeItemById(String itemId) {
        items.removeIf(x -> Objects.equals(x.getId(), itemId));
    }

    public String getGroupId() {
        return groupId;
    }

    public List<ShoppingListItem> getItems() {
        return items;
    }


    private void validate() {
        Validate.notBlank(id);
        Validate.notBlank(groupId);
        Validate.notBlank(name);
    }
}
