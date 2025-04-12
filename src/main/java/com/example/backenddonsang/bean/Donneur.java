package com.example.appGeo.bean;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;
import lombok.Data;
@Entity
@DiscriminatorValue("DONNEUR")
@Data
public class Donneur extends Utilisateur {
  private String groupeSanguin;
  private LocalDate dateDernierDon;
  private int age;
  private String sexe;
  private String conditionsMedicales;
  private double poids;
  @ManyToMany
  private List<RendezVous> rendezVous;

}
