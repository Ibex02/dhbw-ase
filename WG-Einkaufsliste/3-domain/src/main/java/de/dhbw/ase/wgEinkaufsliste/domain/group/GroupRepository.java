package de.dhbw.ase.wgEinkaufsliste.domain.group;

import java.util.Optional;

public interface GroupRepository {
    Optional<Group> findById(String id);
    Group save(Group group);
    void deleteById(String id);
}
