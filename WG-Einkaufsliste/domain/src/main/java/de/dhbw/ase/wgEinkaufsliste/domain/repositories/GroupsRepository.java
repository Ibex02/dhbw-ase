package de.dhbw.ase.wgEinkaufsliste.domain.repositories;

import de.dhbw.ase.wgEinkaufsliste.domain.entities.Group;

import java.util.List;

public interface GroupsRepository {
    Group findGroupById(String id);
    List<Group> findAllGroups();
    void save(Group group);
    void deleteById(String id);
}
