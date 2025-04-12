package com.example.appGeo.service.facade;

import java.util.List;

import com.example.appGeo.bean.Centre;

public interface CentreService {
  int save(Centre centre);
  Centre update(String code, Centre centre);
  List<Centre> findAll();
  Centre findByCode(String code);
  int deleteByCode(String code);
  List<Centre> findByLocalisation(String localisation);
  List<Centre> findByHorairesOuverture(String horairesOuverture);
  Centre findByStockSang_Code(String code);
  List<Centre> findByRendezVous_Code(String code);
  List<Centre> findByQuantites_Code(String code);
  List<Centre> findByLongitude(double longitude);
  List<Centre> findByLatitude(double latitude);
  List<Centre> findCentresProches(double latitude, double longitude, double distance);

}
