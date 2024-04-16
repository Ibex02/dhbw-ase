package de.dhbw.ase.wgEinkaufsliste.adapters.representations.group;

import de.dhbw.ase.wgEinkaufsliste.domain.group.Group;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingList;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListRepository;
import de.dhbw.ase.wgEinkaufsliste.domain.user.User;
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
        return new GroupResource(group.getId(), group.getName(), mapUsers(group.getUsersIds()), mapLists(group.getListIds()));
    }

    private List<GroupResource.GroupResourceUser> mapUsers(List<String> lists) {
        return lists.stream().map(userRepository::findById).filter(Optional::isPresent).map(x -> map(x.get())).toList();
    }

    private GroupResource.GroupResourceUser map(User user) {
        return new GroupResource.GroupResourceUser(user.getId(), user.getName());
    }

    private List<GroupResource.GroupResourceList> mapLists(List<String> lists) {
        return lists.stream().map(shoppingListRepository::findById).filter(Optional::isPresent).map(x -> map(x.get())).toList();
    }

    private GroupResource.GroupResourceList map(ShoppingList list) {
        return new GroupResource.GroupResourceList(list.getId(), list.getName());
    }
}
