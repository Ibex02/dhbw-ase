package de.dhbw.ase.wgEinkaufsliste.adapters.persistence.groups;

import de.dhbw.ase.wgEinkaufsliste.domain.group.Group;
import de.dhbw.ase.wgEinkaufsliste.domain.group.values.GroupId;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListId;
import de.dhbw.ase.wgEinkaufsliste.domain.user.values.UserId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class GroupRecordToGroupMapper implements Function<GroupRecord, Group> {

    @Override
    public Group apply(GroupRecord entity) {
        return map(entity);
    }

    private Group map(GroupRecord entity) {
        var id = new GroupId(entity.id());
        var userIds = mapUserIds(entity.users());
        var listIds = mapListIds(entity.shoppingLists());

        return new Group(id, entity.name(), userIds, listIds);
    }

    private List<UserId> mapUserIds(List<String> ids) {
        return ids.stream().map(UserId::new).toList();
    }

    private List<ShoppingListId> mapListIds(List<String> ids) {
        return ids.stream().map(ShoppingListId::new).toList();
    }
}
