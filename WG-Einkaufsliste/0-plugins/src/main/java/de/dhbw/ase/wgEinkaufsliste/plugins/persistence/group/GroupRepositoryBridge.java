package de.dhbw.ase.wgEinkaufsliste.plugins.persistence.group;

import de.dhbw.ase.wgEinkaufsliste.adapters.persistence.groups.*;
import de.dhbw.ase.wgEinkaufsliste.domain.group.*;
import de.dhbw.ase.wgEinkaufsliste.domain.group.values.GroupId;
import de.dhbw.ase.wgEinkaufsliste.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class GroupRepositoryBridge implements GroupRepository {

    private final MongoGroupRepository repository;
    private final GroupRecordToGroupMapper mapFromRecord;
    private final GroupToGroupRecordMapper mapToRecord;

    @Autowired
    public GroupRepositoryBridge(MongoGroupRepository repository, GroupRecordToGroupMapper mapFromRecord, GroupToGroupRecordMapper mapToRecord) {
        this.repository = repository;
        this.mapFromRecord = mapFromRecord;
        this.mapToRecord = mapToRecord;
    }

    @Override
    public Optional<Group> findById(GroupId id) {
        var record = repository.findById(id.value());
        return record.map(mapFromRecord);
    }

    @Override
    public Collection<Group> findAllWithUser(User user) {
        return user.getGroupIds().stream().map(this::findById)
                .filter(Optional::isPresent).map(Optional::get).toList();
    }

    @Override
    public Group getById(GroupId id) throws GroupNotFoundException {
        return findById(id).orElseThrow(() -> new GroupNotFoundException(id));
    }

    @Override
    public Group save(Group group) {
        var record = mapToRecord.apply(group);
        var saved = repository.save(record);

        return mapFromRecord.apply(saved);
    }

    @Override
    public void deleteById(GroupId id) {
        repository.deleteById(id.value());
    }
}
