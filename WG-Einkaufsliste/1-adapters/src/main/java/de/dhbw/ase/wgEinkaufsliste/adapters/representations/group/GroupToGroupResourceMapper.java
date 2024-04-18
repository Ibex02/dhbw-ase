package de.dhbw.ase.wgEinkaufsliste.adapters.representations.group;

import de.dhbw.ase.wgEinkaufsliste.adapters.representations.group.resource.GroupResource;
import de.dhbw.ase.wgEinkaufsliste.application.shoppingList.ShoppingListManagementService;
import de.dhbw.ase.wgEinkaufsliste.application.user.UserService;
import de.dhbw.ase.wgEinkaufsliste.domain.group.Group;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingList;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListId;
import de.dhbw.ase.wgEinkaufsliste.domain.user.User;
import de.dhbw.ase.wgEinkaufsliste.domain.user.values.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Component
public class GroupToGroupResourceMapper implements Function<Group, GroupResource> {

    private final UserService userService;
    private final ShoppingListManagementService shoppingListService;

    @Autowired
    public GroupToGroupResourceMapper(UserService userService, ShoppingListManagementService shoppingListService) {
        this.userService = userService;
        this.shoppingListService = shoppingListService;
    }

    @Override
    public GroupResource apply(Group group) {
        return map(group);
    }

    private GroupResource map(Group group) {
        var id = group.getId().value();
        var users = mapUsers(group.getUsersIds());
        var shoppingLists = mapLists(group.getListIds());

        return new GroupResource(id, group.getName(), users, shoppingLists);
    }

    private List<GroupResource.GroupResourceUser> mapUsers(Collection<UserId> users) {
        return users.stream().map(userService::findById).filter(Optional::isPresent).map(x -> map(x.get())).toList();
    }

    private GroupResource.GroupResourceUser map(User user) {
        return new GroupResource.GroupResourceUser(user.getId().value(), user.getName());
    }

    private List<GroupResource.GroupResourceList> mapLists(Collection<ShoppingListId> lists) {
        return lists.stream().map(shoppingListService::findById).filter(Optional::isPresent).map(x -> map(x.get())).toList();
    }

    private GroupResource.GroupResourceList map(ShoppingList list) {
        var id = list.getId().value();
        return new GroupResource.GroupResourceList(id, list.getName());
    }
}
