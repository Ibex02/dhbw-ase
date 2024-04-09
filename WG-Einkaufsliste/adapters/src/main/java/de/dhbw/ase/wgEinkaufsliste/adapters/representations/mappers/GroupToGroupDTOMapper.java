package de.dhbw.ase.wgEinkaufsliste.adapters.representations.mappers;

import de.dhbw.ase.wgEinkaufsliste.adapters.representations.GroupDTO;
import de.dhbw.ase.wgEinkaufsliste.domain.entities.Group;

import java.util.function.Function;

public class GroupToGroupDTOMapper implements Function<Group, GroupDTO> {

    @Override
    public GroupDTO apply(Group group) {
        return map(group);
    }

    private GroupDTO map(Group group) {
        return null;
    }
}
