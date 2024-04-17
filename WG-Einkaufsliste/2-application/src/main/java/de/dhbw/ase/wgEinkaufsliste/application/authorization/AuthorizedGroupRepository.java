package de.dhbw.ase.wgEinkaufsliste.application.authorization;

import de.dhbw.ase.wgEinkaufsliste.application.authentication.UserContextProvider;
import de.dhbw.ase.wgEinkaufsliste.domain.group.GroupNotFoundException;
import de.dhbw.ase.wgEinkaufsliste.domain.group.Group;
import de.dhbw.ase.wgEinkaufsliste.domain.group.GroupRepository;
import de.dhbw.ase.wgEinkaufsliste.domain.group.values.GroupId;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorizedGroupRepository {

    private final GroupRepository groupRepository;
    private final UserContextProvider contextProvider;

    public AuthorizedGroupRepository(GroupRepository groupRepository, UserContextProvider contextProvider) {
        this.groupRepository = groupRepository;
        this.contextProvider = contextProvider;
    }

    public Optional<Group> findById(GroupId id) throws NotAuthorizedException {
        var user = contextProvider.getUser();
        if (!user.getGroupIds().contains(id)) throw new NotAuthorizedException();

        return groupRepository.findById(id);
    }

    public Group getById(GroupId id) throws GroupNotFoundException, NotAuthorizedException {
        var groupOpt = findById(id);
        if (groupOpt.isEmpty()) throw new GroupNotFoundException(id);
        return groupOpt.get();
    }
}
