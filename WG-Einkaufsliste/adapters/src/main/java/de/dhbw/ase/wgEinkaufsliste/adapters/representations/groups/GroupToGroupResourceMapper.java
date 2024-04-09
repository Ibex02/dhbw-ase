package de.dhbw.ase.wgEinkaufsliste.adapters.representations.groups;

import de.dhbw.ase.wgEinkaufsliste.domain.entities.Group;
import de.dhbw.ase.wgEinkaufsliste.domain.repositories.ShoppingListRepository;
import de.dhbw.ase.wgEinkaufsliste.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
        return null;
    }
}
