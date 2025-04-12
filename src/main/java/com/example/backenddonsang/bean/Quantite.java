package com.example.appGeo.bean;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Quantite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    @ManyToOne
    private Centre centre;

    @ManyToOne
    private Hopital hopital;

    private String groupeSanguin;
    private int quantite;

    private LocalDateTime dateDemande;
    private boolean approuvee; 
}
