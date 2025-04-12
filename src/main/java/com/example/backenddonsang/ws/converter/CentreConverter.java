package com.example.appGeo.ws.converter;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.example.appGeo.bean.Centre;
import com.example.appGeo.ws.dto.CentreDto;

import java.util.List;

@Component
public class CentreConverter {
  public Centre toBean(CentreDto dto){
    Centre bean=new Centre();
    BeanUtils.copyProperties(dto,bean);
    return bean;
  }
  public List<Centre> toBean(List<CentreDto> dtos){
    return dtos.stream().map(this::toBean).toList();
  }
  public CentreDto toDto(Centre bean){
    CentreDto dto=new CentreDto();
    BeanUtils.copyProperties(bean,dto);
    return dto;
  }
  public List<CentreDto> toDto(List<Centre> beans){
    return beans.stream().map(this::toDto).toList();
  }
}
