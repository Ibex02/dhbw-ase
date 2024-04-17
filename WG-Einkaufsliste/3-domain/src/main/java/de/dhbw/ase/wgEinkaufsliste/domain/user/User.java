package de.dhbw.ase.wgEinkaufsliste.domain.user;

import de.dhbw.ase.wgEinkaufsliste.domain.group.values.GroupId;
import de.dhbw.ase.wgEinkaufsliste.domain.user.values.UserId;
import de.dhbw.ase.wgEinkaufsliste.domain.validator.EmailValidator;
import org.apache.commons.lang3.Validate;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final UserId id;
    private final String email;
    private final String passwordHash;
    private String name;
    private List<GroupId> groupIds = new ArrayList<>();

    public User(UserId id, String email, String passwordHash, String name, List<GroupId> groupIds) {
        this.id = id;
        this.email = email;
        this.passwordHash = passwordHash;
        this.name = name;
        this.groupIds = new ArrayList<>(groupIds);

        validate();
    }

    public User(String email, String passwordHash, String name) {
        this.id = new UserId();
        this.name = name;
        this.passwordHash = passwordHash;
        this.email = email;

        validate();
    }

    public UserId getId() {
        return id;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return passwordHash;
    }
    public String getName() {
        return name;
    }

    public List<GroupId> getGroupIds() {
        return groupIds;
    }

    public void setName(String newName) {
        Validate.notBlank(newName);

        this.name = newName;
    }

    private void validate() {
        Validate.notNull(id, "");
        Validate.notNull(groupIds, "");
        Validate.notBlank(email);
        Validate.notBlank(name);
        Validate.notBlank(passwordHash);

        var emailValidator = EmailValidator.getInstance();
        Validate.isTrue(emailValidator.isValidEmailAddress(email));
    }
}
