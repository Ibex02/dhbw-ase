package de.dhbw.ase.wgEinkaufsliste.adapters.persistence.mappers;

import de.dhbw.ase.wgEinkaufsliste.adapters.persistence.GroupRecord;
import de.dhbw.ase.wgEinkaufsliste.domain.entities.Group;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class GroupRecordToGroupMapper implements Function<GroupRecord, Group> {

    @Override
    public Group apply(GroupRecord entity) {
        return map(entity);
    }

    private Group map(GroupRecord entity) {
        return new Group(entity.getId(), entity.getName(), entity.getUsers(), entity.getLists());
    }

}
