package de.dhbw.ase.wgEinkaufsliste.application.user;

import de.dhbw.ase.wgEinkaufsliste.application.authentication.CustomPasswordEncoder;
import de.dhbw.ase.wgEinkaufsliste.application.group.GroupApplicationService;
import de.dhbw.ase.wgEinkaufsliste.domain.group.GroupRepository;
import de.dhbw.ase.wgEinkaufsliste.domain.user.User;
import de.dhbw.ase.wgEinkaufsliste.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserApplicationService {

    private final CustomPasswordEncoder encoder;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    private final GroupApplicationService groupService;

    @Autowired
    public UserApplicationService(CustomPasswordEncoder encoder, UserRepository userRepository, GroupRepository groupRepository, GroupApplicationService groupService) {
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.groupService = groupService;
    }

    public User create(String email, String password) {
        String encoded = encoder.encode(password);
        var user = new User(email, encoded, "test");

        userRepository.save(user);
        return user;
    }

    public void delete(User user) {
        for (var groupId : user.getGroupIds()) {
            var group = groupRepository.findById(groupId);

            group.removeUser(user);

            if (group.isEmpty()) {
                groupService.delete(group);
            }
        }

        userRepository.deleteById(user.getId());
    }
}
