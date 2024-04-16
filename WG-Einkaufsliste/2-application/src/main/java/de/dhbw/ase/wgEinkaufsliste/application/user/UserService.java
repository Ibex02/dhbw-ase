package de.dhbw.ase.wgEinkaufsliste.application.user;

import de.dhbw.ase.wgEinkaufsliste.application.authentication.PasswordEncoder;
import de.dhbw.ase.wgEinkaufsliste.application.group.GroupService;
import de.dhbw.ase.wgEinkaufsliste.domain.group.GroupRepository;
import de.dhbw.ase.wgEinkaufsliste.domain.user.User;
import de.dhbw.ase.wgEinkaufsliste.domain.user.UserRepository;
import de.dhbw.ase.wgEinkaufsliste.domain.user.values.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final GroupRepository groupRepository;

    private final GroupService groupService;

    @Autowired
    public UserService(PasswordEncoder encoder, UserRepository userRepository, GroupRepository groupRepository, GroupService groupService) {
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.groupRepository = groupRepository;
        this.groupService = groupService;
    }

    public Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User create(String email, String password, String name) throws UserAlreadyExistsException {
        var existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            throw new UserAlreadyExistsException(email);
        }

        String encoded = encoder.encode(password);
        var user = new User(email, encoded, name);

        userRepository.save(user);
        return user;
    }

    public void delete(User user) {
        for (var groupId : user.getGroupIds()) {
            var group = groupRepository.findById(groupId);
            group.ifPresent(value -> groupService.removeUser(value, user));
        }

        userRepository.deleteById(user.getId());
    }

    public User changeName(User user, String newName) {
        user.setName(newName);
        return userRepository.save(user);
    }
}
