package com.example.appGeo.ws.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.appGeo.bean.Utilisateur;
import com.example.appGeo.service.facade.UtilisateurService;
import com.example.appGeo.ws.converter.UtilisateurConverter;
import com.example.appGeo.ws.dto.UtilisateurDto;

import java.util.List;



@RestController
@RequestMapping("api/v1/utilisateur/")
@CrossOrigin("*")
public class UtilisateurProvided {
  @Autowired private UtilisateurConverter converter;
  @Autowired private UtilisateurService service;
  @GetMapping("code/{code}")
  public UtilisateurDto findByCode(@PathVariable String code){
    Utilisateur bean = service.findByCode(code);
    return converter.toDto(bean);
  }
  @GetMapping("nom/{nom}")
  public List<UtilisateurDto> findByNom(@PathVariable String nom){
    List<Utilisateur> beans = service.findByNom(nom);
    return converter.toDto(beans);
  }
  @PostMapping("")
  public int save(@RequestBody UtilisateurDto dto){
      Utilisateur utilisateur=converter.toBean(dto);
      return service.save(utilisateur);
  }
  @GetMapping("")
  public List<UtilisateurDto> findAll(){
      List<Utilisateur> beans= service.findAll();
      return converter.toDto(beans);
  }
  @PutMapping("code/{code}")
  public UtilisateurDto update(@PathVariable String code,@RequestBody UtilisateurDto dto) {
    Utilisateur bean = converter.toBean(dto);
    Utilisateur bean1=service.update(code,bean);  
    return converter.toDto(bean1);
  }
  @DeleteMapping("code/{code}")
  public int deleteByCode(@PathVariable String code){
    return service.deleteByCode(code);
  }

}
