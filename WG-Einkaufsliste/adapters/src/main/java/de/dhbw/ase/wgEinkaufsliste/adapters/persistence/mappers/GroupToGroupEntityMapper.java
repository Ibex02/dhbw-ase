package de.dhbw.ase.wgEinkaufsliste.adapters.persistence.mappers;

import de.dhbw.ase.wgEinkaufsliste.adapters.persistence.GroupEntity;
import de.dhbw.ase.wgEinkaufsliste.domain.entities.Group;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class GroupToGroupEntityMapper implements Function<Group, GroupEntity> {

    @Override
    public GroupEntity apply(Group group) {
        return map(group);
    }

    private GroupEntity map(Group group) {
        return new GroupEntity(group.getId(), group.getName(), group.getUsersIds(), group.getListIds());
    }
}
