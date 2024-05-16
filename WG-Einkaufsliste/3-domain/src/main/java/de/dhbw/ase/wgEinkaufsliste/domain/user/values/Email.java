package de.dhbw.ase.wgEinkaufsliste.domain.user.values;

import de.dhbw.ase.wgEinkaufsliste.domain.validator.EmailValidator;
import org.apache.commons.lang3.Validate;

public record Email(String value) {
    public Email{
        var emailValidator = EmailValidator.getInstance();

        Validate.notBlank(value);
        Validate.isTrue(emailValidator.isValidEmailAddress(value));
    }
}
