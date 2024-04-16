package de.dhbw.ase.wgEinkaufsliste.domain.shoppingList;

import de.dhbw.ase.wgEinkaufsliste.domain.group.Group;
import de.dhbw.ase.wgEinkaufsliste.domain.group.values.GroupId;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListId;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListItemId;
import org.apache.commons.lang3.Validate;

import java.util.ArrayList;
import java.util.List;

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

    public void addOrUpdateItem(ShoppingListItem item) {
        removeItemById(item.id());
        items.add(item);
    }

    public void removeItemById(ShoppingListItemId itemId) {
        items.removeIf(x -> x.id().equals(itemId));
    }

    public GroupId getGroupId() {
        return groupId;
    }

    public List<ShoppingListItem> getItems() {
        return items;
    }

    private void validate() {
        Validate.notNull(id, "");
        Validate.notNull(groupId, "");
        Validate.notBlank(name);
    }
}
