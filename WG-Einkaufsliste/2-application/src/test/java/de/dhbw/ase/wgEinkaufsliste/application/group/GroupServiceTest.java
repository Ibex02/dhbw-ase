package de.dhbw.ase.wgEinkaufsliste.application.group;

import de.dhbw.ase.wgEinkaufsliste.domain.group.Group;
import de.dhbw.ase.wgEinkaufsliste.domain.group.GroupNotFoundException;
import de.dhbw.ase.wgEinkaufsliste.domain.group.GroupRepository;
import de.dhbw.ase.wgEinkaufsliste.domain.group.values.GroupId;
import de.dhbw.ase.wgEinkaufsliste.domain.shoppingList.ShoppingListRepository;
import de.dhbw.ase.wgEinkaufsliste.domain.user.UserRepository;
import de.dhbw.ase.wgEinkaufsliste.domain.user.values.UserId;
import de.dhbw.ase.wgEinkaufsliste.domain.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class GroupServiceTest {
    @Mock
    private GroupRepository groupRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ShoppingListRepository shoppingListRepository;

    @InjectMocks
    private GroupService groupService;

    // Setup some common objects and responses to use in the tests
    private GroupId groupId = new GroupId();
    private UserId userId = new UserId();
    private Group group;
    private User user;

    @BeforeEach
    public void setUp() {
        group = new Group(groupId, "Test Group", List.of(userId), new ArrayList<>());
        user = new User(userId,"user@example.com", "hashedPass", "Test User", List.of(groupId));

        when(groupRepository.findById(groupId)).thenReturn(Optional.of(group));
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(groupRepository.save(group)).thenReturn(group);
        when(userRepository.save(user)).thenReturn(user);
    }

    @Test
    public void getById_Found() {
        Optional<Group> foundGroup = groupService.findById(groupId);
        assertTrue(foundGroup.isPresent());
        assertEquals("Test Group", foundGroup.get().getName());
    }

    @Test
    public void getById_NotFound() {
        when(groupRepository.findById(any())).thenReturn(Optional.empty());
        Optional<Group> foundGroup = groupService.findById(new GroupId());
        assertFalse(foundGroup.isPresent());
    }

    @Test
    public void getAllForUser_HasGroups() {
        List<GroupId> groupIds = Arrays.asList(groupId);
        when(user.getGroupIds()).thenReturn(groupIds);

        List<Group> groups = groupService.getAllForUser(user);
        assertEquals(1, groups.size());
        assertEquals("Test Group", groups.get(0).getName());
    }

    @Test
    public void delete_GroupExists() throws GroupNotFoundException {
        doNothing().when(shoppingListRepository).deleteById(any());
        groupService.delete(groupId);
        verify(groupRepository).deleteById(groupId);
    }

    @Test
    public void delete_GroupNotFound() {
        when(groupRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(GroupNotFoundException.class, () -> {
            groupService.delete(new GroupId());
        });
    }

    @Test
    public void changeName_Success() throws GroupNotFoundException {
        Group updatedGroup = groupService.changeName(groupId, "Updated Name");
        assertEquals("Updated Name", updatedGroup.getName());
        verify(groupRepository).save(any(Group.class));
    }

    @Test
    public void changeName_NotFound() {
        when(groupRepository.findById(any())).thenReturn(Optional.empty());
        assertThrows(GroupNotFoundException.class, () -> {
            groupService.changeName(new GroupId(), "New Name");
        });
    }
}
