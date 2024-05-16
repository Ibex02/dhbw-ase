package de.dhbw.ase.wgEinkaufsliste.adapters.persistence.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("user")
public record UserRecord(@Id String id, @Indexed String email, String password, String name, List<String> groups) { }
