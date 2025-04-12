package com.example.appGeo.service.impl;

import com.example.appGeo.bean.Utilisateur;
import com.example.appGeo.dao.UtilisateurDao;
import com.example.appGeo.service.facade.UtilisateurService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;

@Service
public class UtilisateurImpl implements UtilisateurService {
  private final UtilisateurDao dao;

  @Autowired
  public UtilisateurImpl(UtilisateurDao dao) {
    this.dao = dao;
  }

  @Override
  @Transactional
  public Utilisateur update(String code, Utilisateur utilisateur) {
    Utilisateur u1 = dao.findByCode(code);
    if (u1 == null) {
      throw new EntityNotFoundException("Utilisateur not trouve avec le code " + code);
    }
    Field[] fields = Utilisateur.class.getDeclaredFields();
    for (Field field : fields) {
      field.setAccessible(true);
      Object newValue = ReflectionUtils.getField(field, utilisateur);
      if (newValue != null && !"id".equals(field.getName()) && !"code".equals(field.getName())) {
        ReflectionUtils.setField(field, u1, newValue);
      }
    }

    return dao.save(u1);
  }

  @Override
  public int save(Utilisateur utilisateur) {
    if (findByCode(utilisateur.getCode()) != null) {
      return -1;
    } else {
      dao.save(utilisateur);
      return 1;
    }
  }

  @Override
  public List<Utilisateur> findAll() {
    return dao.findAll();
  }

  @Override
  public Utilisateur findByCode(String code) {
    return dao.findByCode(code);
  }

  @Override
  public List<Utilisateur> findByNom(String nom) {
    return dao.findByNom(nom);
  }

  @Override
  @Transactional
  public int deleteByCode(String code) {
    return dao.deleteByCode(code);
  }


}
