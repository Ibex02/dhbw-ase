package de.dhbw.ase.wgEinkaufsliste.domain.group;

import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingList;
import de.dhbw.ase.wgEinkaufsliste.domain.user.User;
import org.apache.commons.lang3.Validate;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Group {

    private String id;
    private String name;

    private List<String> usersIds = new ArrayList<>();
    private List<String> listIds = new ArrayList<>();

    public Group(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    public Group(String id, String name, List<String> usersIds, List<String> listIds) {
        this.id = id;
        this.name = name;
        this.usersIds = usersIds;
        this.listIds = listIds;
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

    public List<String> getUsersIds() {
        return usersIds;
    }
    public List<String> getListIds() {
        return listIds;
    }

    public void addUser(User user) {
        user.getGroupIds().add(id);
        usersIds.add(user.getId());
    }

    public boolean removeUser(User user) {
        return user.getGroupIds().remove(id) && usersIds.remove(user.getId());
    }

    public boolean isEmpty() {
        return usersIds.isEmpty();
    }

    public void addList(ShoppingList list) {
        listIds.add(list.getId());
    }

    public boolean removeList(ShoppingList list) {
        return listIds.remove(list.getId());
    }

    private void validate() {
        Validate.notBlank(name);
        Validate.notBlank(id);
        Validate.notEmpty(usersIds);
    }
}
