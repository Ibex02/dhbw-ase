package de.dhbw.ase.wgEinkaufsliste.plugins.authentication;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CustomPasswordEncoder implements PasswordEncoder, de.dhbw.ase.wgEinkaufsliste.application.user.PasswordEncoder {

    private final PasswordEncoder encoder;

    public CustomPasswordEncoder() {
        encoder = new BCryptPasswordEncoder();
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return encoder.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }

    @Override
    public String encode(String rawPassword) {
        return encoder.encode(rawPassword);
    }
}
