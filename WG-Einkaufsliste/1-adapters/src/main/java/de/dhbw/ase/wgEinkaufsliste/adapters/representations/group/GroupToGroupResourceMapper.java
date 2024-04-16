package de.dhbw.ase.wgEinkaufsliste.adapters.representations.group;

import de.dhbw.ase.wgEinkaufsliste.domain.group.Group;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingList;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListRepository;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListId;
import de.dhbw.ase.wgEinkaufsliste.domain.user.User;
import de.dhbw.ase.wgEinkaufsliste.domain.user.values.UserId;
import de.dhbw.ase.wgEinkaufsliste.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Component
public class GroupToGroupResourceMapper implements Function<Group, GroupResource> {

    private final UserRepository userRepository;
    private final ShoppingListRepository shoppingListRepository;

    @Autowired
    public GroupToGroupResourceMapper(UserRepository userRepository, ShoppingListRepository shoppingListRepository) {
        this.userRepository = userRepository;
        this.shoppingListRepository = shoppingListRepository;
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

    private List<GroupResource.GroupResourceUser> mapUsers(List<UserId> lists) {
        return lists.stream().map(userRepository::findById).filter(Optional::isPresent).map(x -> map(x.get())).toList();
    }

    private GroupResource.GroupResourceUser map(User user) {
        return new GroupResource.GroupResourceUser(user.getId().value(), user.getName());
    }

    private List<GroupResource.GroupResourceList> mapLists(List<ShoppingListId> lists) {
        return lists.stream().map(shoppingListRepository::findById).filter(Optional::isPresent).map(x -> map(x.get())).toList();
    }

    private GroupResource.GroupResourceList map(ShoppingList list) {
        var id = list.getId().value();
        return new GroupResource.GroupResourceList(id, list.getName());
    }
}
