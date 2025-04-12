package com.example.appGeo.ws.dto;

import java.util.List;

import com.example.appGeo.bean.Quantite;
import com.example.appGeo.bean.RendezVous;
import com.example.appGeo.bean.StockSang;
import lombok.Data;

@Data
public class CentreDto {
    private Long id;
    private String code;
    private String nom;
    private String email;
    private String motDePasse;    
    private String localisation;
    private double latitude;
    private double longitude;
    private String horairesOuverture;
    private StockSang stockSang;
    private List<RendezVous> rendezVous;
    private List<Quantite> quantites;

}
