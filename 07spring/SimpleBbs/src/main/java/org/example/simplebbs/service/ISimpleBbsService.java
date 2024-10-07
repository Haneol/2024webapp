package org.example.simplebbs.service;

import org.apache.ibatis.annotations.Param;
import org.example.simplebbs.dto.SimpleBbsDto;

import java.util.List;

public interface ISimpleBbsService {
    List<SimpleBbsDto> list();
    int count();
    SimpleBbsDto view(String id);
    int write(String writer,String title, String content);
    int delete(String id);
}
