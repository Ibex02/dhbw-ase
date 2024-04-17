package de.dhbw.ase.wgEinkaufsliste.plugins.persistence.group;

import de.dhbw.ase.wgEinkaufsliste.adapters.persistence.groups.GroupRecordToGroupMapper;
import de.dhbw.ase.wgEinkaufsliste.adapters.persistence.groups.GroupToGroupRecordMapper;
import de.dhbw.ase.wgEinkaufsliste.domain.group.GroupNotFoundException;
import de.dhbw.ase.wgEinkaufsliste.domain.group.Group;
import de.dhbw.ase.wgEinkaufsliste.domain.group.GroupRepository;
import de.dhbw.ase.wgEinkaufsliste.domain.group.values.GroupId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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
