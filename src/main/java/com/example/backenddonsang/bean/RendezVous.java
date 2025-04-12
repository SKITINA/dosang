package com.example.appGeo.bean;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
public class RendezVous {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;
    private LocalDateTime dateHeure; 
    @ManyToMany
    private List<Donneur> donneur;
    @ManyToOne
    private Centre centre;
    private String statut;

}
