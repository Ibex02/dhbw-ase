package de.dhbw.ase.wgEinkaufsliste.domain.group;

import java.util.List;

public interface GroupRepository {
    Group findGroupById(String id);
    List<Group> findAllGroups();
    void save(Group group);
    void deleteById(String id);
}
