package com.example.appGeo.ws.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.appGeo.bean.StockSang;
import com.example.appGeo.service.facade.StockSangService;
import com.example.appGeo.ws.converter.StockSangConverter;
import com.example.appGeo.ws.dto.StockSangDto;

import jakarta.transaction.Transactional;

import java.util.List;

@RestController
@RequestMapping("api/v1/stockSang/")
@CrossOrigin("*")
public class StockSangProvided {
  @Autowired private StockSangConverter converter;
  @Autowired private StockSangService service;

  @DeleteMapping("code/{code}")
  @Transactional
  public int deleteByCode(@PathVariable String code){
    return service.deleteByCode(code);
  }
  @GetMapping("groupeSanguin/{groupeSanguin}")
  public List<StockSangDto> findByGroupeSanguin(@PathVariable String groupeSanguin){
    List<StockSang> bean=service.findByGroupeSanguin(groupeSanguin);
    return converter.toDto(bean);
  }
  @GetMapping("code/{code}")
  public StockSangDto findByCode(@PathVariable String code){
    StockSang bean = service.findByCode(code);
    return converter.toDto(bean);
  }
  @PostMapping("")
  public int save(@RequestBody StockSangDto dto){
    StockSang bean = converter.toBean(dto);
    return service.save(bean);
  }
  @GetMapping("")
  public List<StockSangDto> findAll(){
    List<StockSang> beans=service.findAll();
    return converter.toDto(beans);
  }
  @PutMapping("code/{code}")
  public StockSangDto update(@PathVariable String code,@RequestBody StockSangDto dto){
    StockSang bean = converter.toBean(dto);
    StockSang bean2 = service.update(code, bean);
    return converter.toDto(bean2);
  }
  
}
