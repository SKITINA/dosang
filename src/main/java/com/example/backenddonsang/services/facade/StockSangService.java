package com.example.appGeo.service.facade;

import java.util.List;

import com.example.appGeo.bean.StockSang;

public interface StockSangService {
  int save(StockSang stockSang);
  StockSang update(String code, StockSang stockSang);
  List<StockSang> findAll();
  StockSang findByCode(String code);
  List<StockSang> findByGroupeSanguin(String groupeSanguin);
  int deleteByCode(String code);
} 