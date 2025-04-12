package com.example.appGeo.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.example.appGeo.bean.*;
import com.example.appGeo.dao.QuantiteDao;
import com.example.appGeo.dao.RendezVousDao;
import com.example.appGeo.dao.StockSangDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.appGeo.dao.CentreDao;
import com.example.appGeo.service.facade.CentreService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import org.springframework.util.ReflectionUtils;
import java.lang.reflect.Field;
@Service
public class CentreImpl implements CentreService{
  @Autowired private CentreDao dao;
  @Autowired private StockSangDao stockSangDao;
  @Autowired private RendezVousDao rendezVousDao;
  @Autowired private QuantiteDao quantiteDao;

    @Override
    @Transactional
    public Centre update(String code, Centre centre) {
        Centre c1 = dao.findByCode(code);
        if (c1 == null) {
            throw new EntityNotFoundException("Centre non trouvé avec le code : " + code);
        }

        // Mise à jour des champs spécifiques de Centre (via réflexion)
        Field[] fields = Centre.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            // Ne traite pas explicitement la relation StockSang, RendezVous, Quantite ici
            if (!"stockSang".equals(field.getName())
                    && !"rendezVous".equals(field.getName())
                    && !"quantites".equals(field.getName())) {
                Object newValue = ReflectionUtils.getField(field, centre);
                if (newValue != null && !"id".equals(field.getName()) && !"code".equals(field.getName())) {
                    ReflectionUtils.setField(field, c1, newValue);
                }
            }
        }

        Field[] userFields = Utilisateur.class.getDeclaredFields();
        for (Field field : userFields) {
            field.setAccessible(true);
            if (!"id".equals(field.getName()) && !"code".equals(field.getName())) {
                Object newValue = ReflectionUtils.getField(field, centre);
                if (newValue != null) {
                    ReflectionUtils.setField(field, c1, newValue);
                }
            }
        }

        if (centre.getStockSang() != null && centre.getStockSang().getId() != null) {
            StockSang fullStock = stockSangDao.findById(centre.getStockSang().getId())
                    .orElseThrow(() -> new EntityNotFoundException("StockSang non trouvé pour l'id: " + centre.getStockSang().getId()));
            c1.setStockSang(fullStock);
        }

        if (centre.getRendezVous() != null && !centre.getRendezVous().isEmpty()) {
            List<RendezVous> loadedRdv = new ArrayList<>();
            for (RendezVous rdv : centre.getRendezVous()) {
                RendezVous fullRdv = rendezVousDao.findById(rdv.getId())
                        .orElseThrow(() -> new EntityNotFoundException("RendezVous non trouvé pour l'id: " + rdv.getId()));
                loadedRdv.add(fullRdv);
            }
            c1.setRendezVous(loadedRdv);
        }

        if (centre.getQuantites() != null && !centre.getQuantites().isEmpty()) {
            List<Quantite> loadedQuantites = new ArrayList<>();
            for (Quantite q : centre.getQuantites()) {
                Quantite fullQ = quantiteDao.findById(q.getId())
                        .orElseThrow(() -> new EntityNotFoundException("Quantite non trouvée pour l'id: " + q.getId()));
                loadedQuantites.add(fullQ);
            }
            c1.setQuantites(loadedQuantites);
        }

        return dao.save(c1);
    }

    @Override
    public int save(Centre centre) {
        if (centre.getCode() != null && findByCode(centre.getCode()) != null) {
            return -1;
        }
        if (centre.getStockSang() != null && centre.getStockSang().getId() != null) {
            StockSang fullStock = stockSangDao.findById(centre.getStockSang().getId())
                    .orElseThrow(() -> new EntityNotFoundException("StockSang non trouvé pour l'id: " + centre.getStockSang().getId()));
            centre.setStockSang(fullStock);
        }

        if (centre.getRendezVous() != null && !centre.getRendezVous().isEmpty()) {
            List<RendezVous> loadedRendezVous = new ArrayList<>();
            for (RendezVous rdv : centre.getRendezVous()) {
                RendezVous fullRdv = rendezVousDao.findById(rdv.getId())
                        .orElseThrow(() -> new EntityNotFoundException("RendezVous non trouvé pour l'id: " + rdv.getId()));
                loadedRendezVous.add(fullRdv);
            }
            centre.setRendezVous(loadedRendezVous);
        }

        if (centre.getQuantites() != null && !centre.getQuantites().isEmpty()) {
            List<Quantite> loadedQuantites = new ArrayList<>();
            for (Quantite q : centre.getQuantites()) {
                Quantite fullQ = quantiteDao.findById(q.getId())
                        .orElseThrow(() -> new EntityNotFoundException("Quantite non trouvée pour l'id: " + q.getId()));
                loadedQuantites.add(fullQ);
            }
            centre.setQuantites(loadedQuantites);
        }
        dao.save(centre);
        return 1;
    }



    @Override
  public List<Centre> findAll() {
    return dao.findAll();
  }

  @Override
  public Centre findByCode(String code) {
    return dao.findByCode(code);
  }

  @Override
  @Transactional
  public int deleteByCode(String code) {
    return dao.deleteByCode(code);
  }

  @Override
  public List<Centre> findByLocalisation(String localisation) {
    return dao.findByLocalisation(localisation);
  }

  @Override
  public List<Centre> findByHorairesOuverture(String horairesOuverture) {
    return dao.findByHorairesOuverture(horairesOuverture);

  }

  @Override
  public Centre findByStockSang_Code(String code) {
    return dao.findByStockSang_Code(code);

  }

  @Override
  public List<Centre> findByRendezVous_Code(String code) {
    return dao.findByRendezVous_Code(code);

  }

  @Override
  public List<Centre> findByQuantites_Code(String code) {
    return dao.findByQuantites_Code(code);

  }

  @Override
  public List<Centre> findByLongitude(double longitude) {
    return dao.findByLongitude(longitude);

  }

  @Override
  public List<Centre> findByLatitude(double latitude) {
    return dao.findByLatitude(latitude);

  }

  @Override
  public List<Centre> findCentresProches(double latitude, double longitude, double distance) {
    return dao.findCentresProches(latitude,longitude,distance);

  }

  
}
