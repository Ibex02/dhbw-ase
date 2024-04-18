package de.dhbw.ase.wgEinkaufsliste.application.user;

import de.dhbw.ase.wgEinkaufsliste.application.group.GroupUserService;
import de.dhbw.ase.wgEinkaufsliste.application.group.event.GroupCreatedEvent;
import de.dhbw.ase.wgEinkaufsliste.application.group.event.GroupDeletedEvent;
import de.dhbw.ase.wgEinkaufsliste.application.user.command.CreateUserCommand;
import de.dhbw.ase.wgEinkaufsliste.domain.user.User;
import de.dhbw.ase.wgEinkaufsliste.domain.user.UserRepository;
import de.dhbw.ase.wgEinkaufsliste.domain.user.values.UserId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final GroupUserService groupService;

    @Autowired
    public UserService(PasswordEncoder encoder, UserRepository userRepository, GroupUserService groupService) {
        this.encoder = encoder;
        this.userRepository = userRepository;
        this.groupService = groupService;
    }

    public Optional<User> findById(UserId id) {
        return userRepository.findById(id);
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User create(CreateUserCommand command) throws UserAlreadyExistsException {
        var email = command.email();
        var password = command.password();
        var name = command.name();

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
        groupService.getAllForUser(user).forEach(x -> groupService.removeUser(x, user));
        userRepository.deleteById(user.getId());
    }

    public User changeName(User user, String newName) {
        user.setName(newName);
        return userRepository.save(user);
    }

    @EventListener
    public void handleGroupCreated(GroupCreatedEvent event) {
    }

    @EventListener
    public void handleGroupDeleted(GroupDeletedEvent event) {
        var group = event.getGroup();
        var users = group.getUsersIds().stream().map(this::findById).filter(Optional::isPresent).map(Optional::get).toList();
        for (var user : users) {
            user.removeFromGroup(group.getId());
            userRepository.save(user);
        }
    }
}