package de.dhbw.ase.wgEinkaufsliste.application.authentication;

import de.dhbw.ase.wgEinkaufsliste.domain.user.User;

public interface UserContextProvider {
    User getUser();
}
