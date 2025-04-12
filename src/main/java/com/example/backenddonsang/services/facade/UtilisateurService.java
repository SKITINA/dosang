package com.example.appGeo.service.facade;

import java.util.List;

import com.example.appGeo.bean.Utilisateur;

public interface UtilisateurService {
  int save(Utilisateur utilisateur);
  List<Utilisateur> findAll();
  Utilisateur findByCode(String code);
  List<Utilisateur> findByNom(String nom);
  int deleteByCode(String code);
  Utilisateur update(String code, Utilisateur utilisateur);
  
} 