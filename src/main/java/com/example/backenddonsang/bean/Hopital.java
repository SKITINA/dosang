package com.example.appGeo.bean;

import jakarta.persistence.*;

import java.util.List;
import java.util.Map;
import lombok.Data;

@Entity
@DiscriminatorValue("HOPITAL")
@Data
public class Hopital extends Utilisateur {
    
    private String localisation;
    private double latitude;
    private double longitude;
    @OneToMany
    private List<Quantite> quantites;
    @ElementCollection
    private Map<String, Integer> besoinsUrgents; // Groupes sanguins en pénurie

}
