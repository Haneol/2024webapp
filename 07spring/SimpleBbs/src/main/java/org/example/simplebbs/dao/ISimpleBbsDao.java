package org.example.simplebbs.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.example.simplebbs.dto.SimpleBbsDto;

@Mapper
public interface ISimpleBbsDao {
    List<SimpleBbsDto> listDao();
    int countDao();
    SimpleBbsDto viewDao(String id);
    int writeDao(String writer,String title, String content);
    int delete(@Param("id") String id);
}
