package de.dhbw.ase.wgEinkaufsliste.application.authentication;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordEncoder {
    private final BCryptPasswordEncoder encoder;

    public PasswordEncoder() {
        encoder = new BCryptPasswordEncoder();
    }

    public String encode(String password) {
        return encoder.encode(password);
    }
}
