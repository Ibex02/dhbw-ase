package de.dhbw.ase.wgEinkaufsliste.adapters.persistence.groups;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("group")
public record GroupRecord(@Id String id, String name, List<String> users, List<String> shoppingLists) { }
