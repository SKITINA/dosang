package com.example.appGeo.ws.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.appGeo.bean.Centre;
import com.example.appGeo.service.facade.CentreService;
import com.example.appGeo.ws.converter.CentreConverter;
import com.example.appGeo.ws.dto.CentreDto;

import jakarta.transaction.Transactional;

import java.util.List;

@RestController
@RequestMapping("api/v1/centre/")
@CrossOrigin("*")
public class CentreProvided {
  @Autowired private CentreConverter converter;
  @Autowired private CentreService service;
  @GetMapping("code/{code}")
  public CentreDto findByCode(@PathVariable String code){
    Centre bean = service.findByCode(code);
    return converter.toDto(bean);
  }
  @GetMapping("localisation/{localisation}")
  public List<CentreDto> findByLocalisation(@PathVariable String localisation){
     List<Centre> beans=service.findByLocalisation(localisation);
     return converter.toDto(beans);
  }
  @GetMapping("horairesOuverture/{horairesOuverture}")
  public List<CentreDto> findByHorairesOuverture(@PathVariable String horairesOuverture){
    List<Centre> beans=service.findByHorairesOuverture(horairesOuverture);
    return converter.toDto(beans);
  }
  @GetMapping("latitude/{latitude}/longitude/{longitude}/distance/{distance}")
  public List<CentreDto> findCentresProches(@PathVariable double latitude,@PathVariable double longitude,@PathVariable double distance){
    List<Centre> beans=service.findCentresProches(latitude,longitude,distance);
    return converter.toDto(beans);
  }
  @GetMapping("longitude/{longitude}")
  public List<CentreDto> findByLongitude(@PathVariable double longitude){
    List<Centre> beans=service.findByLongitude(longitude);
    return converter.toDto(beans);
  }
  @GetMapping("latitude/{latitude}")
  public List<CentreDto> findByLatitude(@PathVariable double latitude){
    List<Centre> beans=service.findByLatitude(latitude);
    return converter.toDto(beans);
  }
  @GetMapping("StockSang/code/{code}")
  public CentreDto findByStockSang_Code(@PathVariable String code){
    Centre bean=service.findByStockSang_Code(code);
    return converter.toDto(bean);
  }
  @GetMapping("RendezVous/code/{code}")
  public List<CentreDto> findByRendezVous_Code(@PathVariable String code){
    List<Centre> beans=service.findByRendezVous_Code(code);
    return converter.toDto(beans);
  }
  @GetMapping("Quantites/code/{code}")
  public List<CentreDto> findByQuantites_Code(@PathVariable String code){
    List<Centre> beans=service.findByQuantites_Code(code);
    return converter.toDto(beans);
  }
  

  @PostMapping("")
  public int save(@RequestBody CentreDto dto){
    Centre bean = converter.toBean(dto);
    return service.save(bean);
  }
  @GetMapping("")
  public List<CentreDto> findAll(){
    List<Centre> beans = service.findAll();
    return converter.toDto(beans);
  }
  @DeleteMapping("code/{code}")
  @Transactional
  public int deleteByCode(@PathVariable String code){
    return service.deleteByCode(code);
  }
  @PutMapping("code/{code}")
  public CentreDto update(@PathVariable String code,@RequestBody CentreDto dto){
    Centre bean = converter.toBean(dto);
    Centre bean1 = service.update(code,bean);
    return converter.toDto(bean1);
  }
}
