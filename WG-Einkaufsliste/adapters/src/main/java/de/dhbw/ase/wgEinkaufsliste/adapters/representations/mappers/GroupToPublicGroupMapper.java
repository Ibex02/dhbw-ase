package de.dhbw.ase.wgEinkaufsliste.adapters.representations.mappers;

import de.dhbw.ase.wgEinkaufsliste.adapters.representations.PublicGroup;
import de.dhbw.ase.wgEinkaufsliste.domain.entities.Group;

import java.util.function.Function;

public class GroupToPublicGroupMapper implements Function<Group, PublicGroup> {

    @Override
    public PublicGroup apply(Group group) {
        return map(group);
    }

    private PublicGroup map(Group group) {
        return null;
    }
}
