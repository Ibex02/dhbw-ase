package de.dhbw.ase.wgEinkaufsliste.application.authentication;

public interface CustomPasswordEncoder {
    String encode(String rawPassword);
}
