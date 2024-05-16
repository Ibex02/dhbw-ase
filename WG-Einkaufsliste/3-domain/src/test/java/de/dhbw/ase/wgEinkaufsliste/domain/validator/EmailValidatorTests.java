package de.dhbw.ase.wgEinkaufsliste.domain.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailValidatorTests {

    @Test
    public void testValidEmail() {
        EmailValidator validator = EmailValidator.getInstance();
        assertTrue(validator.isValidEmailAddress("test@example.com"));
        assertTrue(validator.isValidEmailAddress("user123@domain.co.in"));
        assertTrue(validator.isValidEmailAddress("user-123@example.co.uk"));
        assertTrue(validator.isValidEmailAddress("user.name@example.com"));
    }

    @Test
    public void testInvalidEmail() {
        EmailValidator validator = EmailValidator.getInstance();
        assertFalse(validator.isValidEmailAddress("invalidemail"));
        assertFalse(validator.isValidEmailAddress("invalid.value@"));
        assertFalse(validator.isValidEmailAddress("invalid@.com"));
        assertFalse(validator.isValidEmailAddress("invalid@domain"));
    }

    @Test
    public void testNullEmail() {
        EmailValidator validator = EmailValidator.getInstance();
        assertFalse(validator.isValidEmailAddress(null));
    }
}
