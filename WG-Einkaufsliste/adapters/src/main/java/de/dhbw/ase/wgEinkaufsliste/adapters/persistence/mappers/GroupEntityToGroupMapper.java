package de.dhbw.ase.wgEinkaufsliste.adapters.persistence.mappers;

import de.dhbw.ase.wgEinkaufsliste.adapters.persistence.GroupEntity;
import de.dhbw.ase.wgEinkaufsliste.domain.entities.Group;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class GroupEntityToGroupMapper implements Function<GroupEntity, Group> {

    @Override
    public Group apply(GroupEntity entity) {
        return map(entity);
    }

    private Group map(GroupEntity entity) {
        return new Group(entity.getId(), entity.getName(), entity.getUsersIds(), entity.getListIds());
    }

}
