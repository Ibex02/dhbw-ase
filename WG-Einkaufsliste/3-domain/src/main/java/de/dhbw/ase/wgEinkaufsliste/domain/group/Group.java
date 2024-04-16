package de.dhbw.ase.wgEinkaufsliste.domain.group;

import de.dhbw.ase.wgEinkaufsliste.domain.group.values.GroupId;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingList;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListId;
import de.dhbw.ase.wgEinkaufsliste.domain.user.User;
import de.dhbw.ase.wgEinkaufsliste.domain.user.values.UserId;
import org.apache.commons.lang3.Validate;

import java.util.ArrayList;
import java.util.List;

public class Group {

    private final GroupId id;
    private String name;

    private List<UserId> usersIds = new ArrayList<>();
    private List<ShoppingListId> listIds = new ArrayList<>();

    public Group(String name) {
        this.id = new GroupId();
        this.name = name;

        validate();
    }

    public Group(GroupId id, String name, List<UserId> usersIds, List<ShoppingListId> listIds) {
        this.id = id;
        this.name = name;
        this.usersIds = usersIds;
        this.listIds = listIds;

        validate();
    }

    public GroupId getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        Validate.notBlank(name);

        this.name = name;
    }

    public List<UserId> getUsersIds() {
        return usersIds;
    }
    public List<ShoppingListId> getListIds() {
        return listIds;
    }

    public void addUser(User user) {
        var userGroupIds = user.getGroupIds();
        if (!userGroupIds.contains(id)) {
            user.getGroupIds().add(id);
        }
        if (!usersIds.contains(user.getId())) {
            usersIds.add(user.getId());
        }
    }

    public boolean removeUser(User user) {
        return user.getGroupIds().remove(id) & usersIds.remove(user.getId());
    }

    public boolean isEmpty() {
        return usersIds.isEmpty();
    }

    public void addList(ShoppingList list) {
        if (!listIds.contains(list.getId())) {
            listIds.add(list.getId());
        }
    }

    public boolean removeList(ShoppingList list) {
        return listIds.remove(list.getId());
    }

    private void validate() {
        Validate.notNull(id, "");
        Validate.notBlank(name);
        Validate.notEmpty(usersIds);
        Validate.notNull(listIds, "");
    }
}
