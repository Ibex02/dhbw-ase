package de.dhbw.ase.wgEinkaufsliste.plugins.persistence.groups;

import de.dhbw.ase.wgEinkaufsliste.adapters.persistence.GroupRecord;
import de.dhbw.ase.wgEinkaufsliste.adapters.persistence.mappers.GroupRecordToGroupMapper;
import de.dhbw.ase.wgEinkaufsliste.adapters.persistence.mappers.GroupToGroupRecordMapper;
import de.dhbw.ase.wgEinkaufsliste.domain.entities.Group;
import de.dhbw.ase.wgEinkaufsliste.domain.repositories.GroupsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupRepositoryBridge implements GroupsRepository {

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
    public Group findGroupById(String id) {
        var result = repository.findById(id);
        if (result.isPresent()) {
            return mapFromRecord.apply(result.get());
        }
        return null;
    }

    @Override
    public List<Group> findAllGroups() {
        return repository.findAll().stream().map(mapFromRecord).toList();
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
