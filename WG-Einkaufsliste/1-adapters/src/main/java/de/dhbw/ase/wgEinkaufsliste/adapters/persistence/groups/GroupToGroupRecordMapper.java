package de.dhbw.ase.wgEinkaufsliste.adapters.persistence.groups;

import de.dhbw.ase.wgEinkaufsliste.domain.group.Group;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.values.ShoppingListId;
import de.dhbw.ase.wgEinkaufsliste.domain.user.values.UserId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class GroupToGroupRecordMapper implements Function<Group, GroupRecord> {

    @Override
    public GroupRecord apply(Group group) {
        return mapUserIds(group);
    }

    private GroupRecord mapUserIds(Group group) {
        var id = group.getId().value();
        var userIds = mapUserIds(group.getUsersIds());
        var listIds = mapListIds(group.getListIds());

        return new GroupRecord(id, group.getName(), userIds, listIds);
    }

    private List<String> mapUserIds(List<UserId> ids) {
        return ids.stream().map(UserId::value).toList();
    }

    private List<String> mapListIds(List<ShoppingListId> ids) {
        return ids.stream().map(ShoppingListId::value).toList();
    }
}
