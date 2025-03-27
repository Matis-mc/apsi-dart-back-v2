package org.apsidart.player.player.entity;

import java.util.List;

import org.apsidart.player.organisation.entity.OrganisationEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

/**
 * La représentation d'un utilisateur de l'application
 */
@Entity
@Table(name = "player")
public class PlayerEntity {
    
    @Id @GeneratedValue
    private Long id;  

    private String firstName;
    
    private String lastName;

    private String pseudo;

    @ManyToMany
    private List<OrganisationEntity> organisations;

    public PlayerEntity(Long id, String firstName, String lastName, String pseudo) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.pseudo = pseudo;
    }

    public PlayerEntity(String firstName, String lastName, String pseudo) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.pseudo = pseudo;
    }

    public PlayerEntity(){};

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    @Override
    public String toString() {
        return new StringBuilder("PlayerEntity [id=").append(id)
            .append(", firstName=").append(firstName)
            .append(", lastName=").append(lastName)
            .append(", pseudo=").append(pseudo)
            .append("]")
            .toString();
    }
}
