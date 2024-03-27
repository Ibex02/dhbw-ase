package de.dhbw.ase.wgEinkaufsliste.domain.values;

public class Password {
    private String hash;

    public Password(String hash) {
        this.hash = hash;
    }

    public String getHash() {
        return hash;
    }
}
