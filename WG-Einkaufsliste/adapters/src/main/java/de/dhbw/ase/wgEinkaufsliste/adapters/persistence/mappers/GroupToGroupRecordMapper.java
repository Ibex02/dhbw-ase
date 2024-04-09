package de.dhbw.ase.wgEinkaufsliste.adapters.persistence.mappers;

import de.dhbw.ase.wgEinkaufsliste.adapters.persistence.GroupRecord;
import de.dhbw.ase.wgEinkaufsliste.domain.entities.Group;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class GroupToGroupRecordMapper implements Function<Group, GroupRecord> {

    @Override
    public GroupRecord apply(Group group) {
        return map(group);
    }

    private GroupRecord map(Group group) {
        return new GroupRecord(group.getId(), group.getName(), group.getUsersIds(), group.getListIds());
    }
}
