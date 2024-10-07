package org.example.simplebbs.service;

import lombok.RequiredArgsConstructor;
import org.example.simplebbs.dao.ISimpleBbsDao;
import org.example.simplebbs.dto.SimpleBbsDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // Constructor Dependency Injection
public class SimpleBbsService implements ISimpleBbsService {
    private final ISimpleBbsDao dao;

    @Override
    public List<SimpleBbsDto> list() {
        return dao.listDao();
    }

    @Override
    public int count() {
        return dao.countDao();
    }

    @Override
    public SimpleBbsDto view(String id) {
        return dao.viewDao(id);
    }

    @Override
    public int write(String writer, String title, String content) {
        return dao.writeDao(writer, title, content);
    }

    @Override
    public int delete(String id) {
        return dao.delete(id);
    }
}
