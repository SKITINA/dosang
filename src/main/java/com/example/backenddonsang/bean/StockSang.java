package com.example.appGeo.bean;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class StockSang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String code;

    @OneToMany
    private List<Centre> centre;

    private String groupeSanguin;
    private int quantite;
    private LocalDate dateMiseAJour;

}
