package com.example.appGeo.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.appGeo.bean.Centre;
import com.example.appGeo.bean.StockSang;
import com.example.appGeo.dao.CentreDao;
import com.example.appGeo.dao.StockSangDao;
import com.example.appGeo.service.facade.StockSangService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
@Service
public class StockSangImpl implements StockSangService {
  @Autowired private StockSangDao dao;
  @Autowired private CentreDao centreDao;
  @Override
  public int save(StockSang stockSang) {
    if (stockSang.getCode() == null || stockSang.getCode().isEmpty()) {
        stockSang.setCode(UUID.randomUUID().toString());
    }
    if (findByCode(stockSang.getCode()) != null) {
        return -1;
    } else {
        if (stockSang.getCentre() != null && !stockSang.getCentre().isEmpty()) {
            List<Centre> centresFromDb = new ArrayList<>();
            for (Centre centre : stockSang.getCentre()) {
                Centre fullCentre = centreDao.findById(centre.getId())
                    .orElseThrow(() -> new EntityNotFoundException("Centre non trouvé pour l'id: " + centre.getId()));
                centresFromDb.add(fullCentre);
            }
            stockSang.setCentre(centresFromDb);
        }
        dao.save(stockSang);
        return 1;
    }
  }


  @Override
  @Transactional 
  public StockSang update(String code, StockSang stockSang) { 
    StockSang ss1 = dao.findByCode(code);
    if(ss1 == null) {
        throw new EntityNotFoundException("StockSang non trouvé avec le code " + code);
    }

    if(stockSang.getGroupeSanguin() != null) {
        ss1.setGroupeSanguin(stockSang.getGroupeSanguin());
    }

    if(stockSang.getQuantite() != 0) {
        ss1.setQuantite(stockSang.getQuantite());
    }

    if(stockSang.getDateMiseAJour() != null) {
        ss1.setDateMiseAJour(stockSang.getDateMiseAJour());
    }

    if(stockSang.getCentre() != null && !stockSang.getCentre().isEmpty()) {
        List<Centre> centresFromDb = new ArrayList<>();
        for(Centre centre : stockSang.getCentre()) {
            Centre fullCentre = centreDao.findById(centre.getId())
                .orElseThrow(() -> new EntityNotFoundException("Centre non trouvé pour l'id: " + centre.getId()));
            centresFromDb.add(fullCentre);
        }
        ss1.setCentre(centresFromDb);
    }

    return dao.save(ss1);
}



  @Override
  public List<StockSang> findAll() {
    return dao.findAll();
  }

  @Override
  public StockSang findByCode(String code) {
    return dao.findByCode(code);
  }

  @Override
  public List<StockSang> findByGroupeSanguin(String groupeSanguin) {
    return dao.findByGroupeSanguin(groupeSanguin);
  }

  @Override
  @Transactional
  public int deleteByCode(String code) {
    return dao.deleteByCode(code);
  }
  
}
