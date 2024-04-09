package de.dhbw.ase.wgEinkaufsliste.domain.group;

public interface GroupRepository {
    Group findById(String id);
    void save(Group group);
    void deleteById(String id);
}
