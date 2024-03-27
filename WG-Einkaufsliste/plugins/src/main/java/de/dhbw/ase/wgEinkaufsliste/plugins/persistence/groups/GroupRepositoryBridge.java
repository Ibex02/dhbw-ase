package de.dhbw.ase.wgEinkaufsliste.plugins.persistence.groups;

import de.dhbw.ase.wgEinkaufsliste.adapters.persistence.GroupEntity;
import de.dhbw.ase.wgEinkaufsliste.adapters.persistence.mappers.GroupEntityToGroupMapper;
import de.dhbw.ase.wgEinkaufsliste.adapters.persistence.mappers.GroupToGroupEntityMapper;
import de.dhbw.ase.wgEinkaufsliste.domain.entities.Group;
import de.dhbw.ase.wgEinkaufsliste.domain.repositories.GroupsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GroupRepositoryBridge implements GroupsRepository {

    private final SpringDateGroupRepository repository;
    private final GroupEntityToGroupMapper mapFromEntity;
    private final GroupToGroupEntityMapper mapToEntity;

    @Autowired
    public GroupRepositoryBridge(SpringDateGroupRepository repository, GroupEntityToGroupMapper mapFromEntity, GroupToGroupEntityMapper mapToEntity) {
        this.repository = repository;
        this.mapFromEntity = mapFromEntity;
        this.mapToEntity = mapToEntity;
    }

    @Override
    public Group findGroupById(String id) {
        var result = repository.findById(id);
        if (result.isPresent()) {
            return mapFromEntity.apply(result.get());
        }
        return null;
    }

    @Override
    public List<Group> findAllGroups() {
        return repository.findAll().stream().map(mapFromEntity).toList();
    }

    @Override
    public void save(Group group) {
        GroupEntity entity = mapToEntity.apply(group);
        repository.save(entity);
    }

    @Override
    public void deleteById(String id) {

    }
}
