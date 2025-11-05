package nl.benzelinsky.techiteasybackend.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Role {
    @Id
    private Long id;
    private String name;
}
