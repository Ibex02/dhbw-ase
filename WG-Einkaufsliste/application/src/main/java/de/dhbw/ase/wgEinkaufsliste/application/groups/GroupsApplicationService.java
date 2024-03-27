package de.dhbw.ase.wgEinkaufsliste.application.groups;

import de.dhbw.ase.wgEinkaufsliste.domain.entities.Group;
import de.dhbw.ase.wgEinkaufsliste.domain.repositories.GroupsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupsApplicationService {
    private final GroupsRepository repository;

    @Autowired
    public GroupsApplicationService(GroupsRepository repository) {
        this.repository = repository;
    }

    public Group createNewGroup() {
        Group group = new Group("Test");
        repository.save(group);

        return group;
    }
}
