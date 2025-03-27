package org.apsidart.player.organisation.entity;

import java.time.LocalDate;
import java.util.List;

import org.apsidart.player.player.entity.PlayerEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

/**
 * La représentation d'une organisation dans l'application
 */
@Entity
@Table(name = "organisation")
public class OrganisationEntity {

    @Id @GeneratedValue
    private Long id;

    String libelle;

    LocalDate dateCreation;

    @ManyToMany
    List<PlayerEntity> players;

    public Long getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public List<PlayerEntity> getPlayers() {
        return players;
    }

    public OrganisationEntity(String libelle, LocalDate dateCreation, List<PlayerEntity> players) {
        this.libelle = libelle;
        this.dateCreation = dateCreation;
        this.players = players;
    }

    @Override
    public String toString() {
        return "OrganisationEntity [id=" + id + ", libelle=" + libelle + ", dateCreation=" + dateCreation + ", players="
                + players + "]";
    }

    
}
