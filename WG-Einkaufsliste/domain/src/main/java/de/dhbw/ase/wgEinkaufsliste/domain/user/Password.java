package de.dhbw.ase.wgEinkaufsliste.domain.user;

public class Password {
    private String hash;

    public Password(String hash) {
        this.hash = hash;
    }

    public String getHash() {
        return hash;
    }
}
