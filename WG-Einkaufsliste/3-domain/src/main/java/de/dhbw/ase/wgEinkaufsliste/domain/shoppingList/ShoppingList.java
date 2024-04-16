package de.dhbw.ase.wgEinkaufsliste.domain.shoppingList;

import de.dhbw.ase.wgEinkaufsliste.domain.group.Group;
import de.dhbw.ase.wgEinkaufsliste.domain.group.values.GroupId;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListId;
import org.apache.commons.lang3.Validate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ShoppingList {
    private final ShoppingListId id;
    private final GroupId groupId;
    private String name;
    private List<ShoppingListItem> items = new ArrayList<>();

    public ShoppingList(ShoppingListId id, GroupId groupId, String name, List<ShoppingListItem> items) {
        this.id = id;
        this.groupId = groupId;
        this.name = name;
        this.items = items;

        validate();
    }

    public ShoppingList(Group group, String name) {

        this.id = new ShoppingListId();
        this.name = name;

        group.addList(this);
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

    public void addItem(ShoppingListItem item) {
        items.add(item);
    }

    public void removeItemById(String itemId) {
        items.removeIf(x -> Objects.equals(x.getId(), itemId));
    }

    public GroupId getGroupId() {
        return groupId;
    }

    public List<ShoppingListItem> getItems() {
        return items;
    }


    private void validate() {
        Validate.notNull(id, "value must not be null!");
        Validate.notNull(groupId, "groupId must not be null!");
        Validate.notBlank(name);
    }
}
