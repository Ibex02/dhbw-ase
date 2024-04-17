package de.dhbw.ase.wgEinkaufsliste.domain.user;


import de.dhbw.ase.wgEinkaufsliste.domain.group.values.GroupId;
import de.dhbw.ase.wgEinkaufsliste.domain.user.values.UserId;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    // Test the full constructor initialization
    @Test
    public void testUserFullConstructor() {
        UserId userId = new UserId();
        List<GroupId> groups = Arrays.asList(new GroupId(), new GroupId());
        User user = new User(userId, "test@example.com", "hashedpassword", "John Doe", groups);

        assertNotNull(user.getId());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("hashedpassword", user.getPassword());
        assertEquals("John Doe", user.getName());
        assertEquals(2, user.getGroupIds().size());
    }

    // Test the simpler constructor and the generation of UserId
    @Test
    public void testUserSimpleConstructor() {
        User user = new User("test@example.com", "hashedpassword", "John Doe");

        assertNotNull(user.getId());
        assertEquals("test@example.com", user.getEmail());
        assertEquals("hashedpassword", user.getPassword());
        assertEquals("John Doe", user.getName());
        assertTrue(user.getGroupIds().isEmpty());
    }

    // Test validation logic for blank fields
    @Test
    public void testValidationForBlankFields() {
        assertThrows(IllegalArgumentException.class, () -> {
            new User(" ", "hashedpassword", "John Doe");
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new User("test@example.com", " ", "John Doe");
        });
    }

    @Test
    public void testValidationForInvalidEmail() {
        assertThrows(IllegalArgumentException.class, () -> {
            new User("invalid_email", "hashedpassword", "John Doe");
        });
    }

    @Test
    public void testSetName() {
        User user = new User("test@example.com", "hashedpassword", "John Doe");
        user.setName("Jane Doe");
        assertEquals("Jane Doe", user.getName());
    }

    @Test
    public void testSetNameInvalid() {
        User user = new User("test@example.com", "hashedpassword", "John Doe");
        assertThrows(IllegalArgumentException.class, () -> {
            user.setName(" ");
        });
    }
}
