package de.dhbw.ase.wgEinkaufsliste.application.group;

import de.dhbw.ase.wgEinkaufsliste.domain.group.Group;
import de.dhbw.ase.wgEinkaufsliste.domain.group.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GroupApplicationService {
    private final GroupRepository repository;

    @Autowired
    public GroupApplicationService(GroupRepository repository) {
        this.repository = repository;
    }

    public Group createNewGroup() {
        Group group = new Group("Test");
        repository.save(group);

        return group;
    }
}
