package de.dhbw.ase.wgEinkaufsliste.adapters.persistence.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document("Users")
public class UserRecord {
    @Id
    private String id;
    private String userName;
    private String password;

    private String displayName;
    private List<String> groups = new ArrayList<>();

    public UserRecord(String id, String userName, String password, String displayName, List<String> groups) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.displayName = displayName;
        this.groups = groups;
    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getDisplayName() {
        return displayName;
    }

    public List<String> getGroups() {
        return groups;
    }
}
