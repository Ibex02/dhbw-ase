package de.dhbw.ase.wgEinkaufsliste.domain.group;

import de.dhbw.ase.wgEinkaufsliste.domain.group.values.GroupId;

import java.util.Optional;

public interface GroupRepository {
    Optional<Group> findById(GroupId id);
    Group save(Group group);
    void deleteById(GroupId id);
}
