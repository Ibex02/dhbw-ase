package de.dhbw.ase.wgEinkaufsliste.application.user;

import de.dhbw.ase.wgEinkaufsliste.domain.user.User;

public interface CurrentUserProvider {
    User getUser();
}
