package org.apsidart.player.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * La reprsentation d'un utilisateur de l'application
 */
@Entity
@Table(name = "player")
public class PlayerEntity {
    
    @Id @GeneratedValue private Long id;  

    public String firstName;
    public String lastName;
    public String pseudo;

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

    
    
    /* 
    @OneToMany
    private List<DPerform> dPerform;
    */

    


}
