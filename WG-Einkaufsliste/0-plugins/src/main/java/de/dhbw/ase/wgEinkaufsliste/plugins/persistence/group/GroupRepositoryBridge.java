package de.dhbw.ase.wgEinkaufsliste.plugins.persistence.group;

import de.dhbw.ase.wgEinkaufsliste.adapters.persistence.groups.GroupRecord;
import de.dhbw.ase.wgEinkaufsliste.adapters.persistence.groups.GroupRecordToGroupMapper;
import de.dhbw.ase.wgEinkaufsliste.adapters.persistence.groups.GroupToGroupRecordMapper;
import de.dhbw.ase.wgEinkaufsliste.domain.group.Group;
import de.dhbw.ase.wgEinkaufsliste.domain.group.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public Group findById(String id) {
        var result = repository.findById(id);
        return result.map(mapFromRecord).orElse(null);
    }

    @Override
    public void save(Group group) {
        GroupRecord record = mapToRecord.apply(group);
        repository.save(record);
    }

    @Override
    public void deleteById(String id) {

    }
}
