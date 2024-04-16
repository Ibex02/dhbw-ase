package de.dhbw.ase.wgEinkaufsliste.application.authentication;

public interface PasswordEncoder {
    String encode(String rawPassword);
}
