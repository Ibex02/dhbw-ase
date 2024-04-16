package de.dhbw.ase.wgEinkaufsliste.domain.validator;

import java.util.regex.Pattern;

public class EmailValidator {
    private static final EmailValidator instance = new EmailValidator();

    private final Pattern pattern;

    private EmailValidator() {
        var regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}";
        pattern = Pattern.compile(regex);
    }

    public boolean isValidEmailAddress(String email) {
        var matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static EmailValidator getInstance() {
        return instance;
    }
}
