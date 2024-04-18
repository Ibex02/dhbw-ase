package de.dhbw.ase.wgEinkaufsliste.application.user;

public interface PasswordEncoder {
    String encode(String rawPassword);
}
