package org.example.jpacrud.service;

import lombok.RequiredArgsConstructor;
import org.example.jpacrud.entity.TestEntity;
import org.example.jpacrud.repository.TestRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TestService {
    private final TestRepository testRepository;

    public void create(String name, Integer age) {
        TestEntity testEntity = new TestEntity(name, age);
        testRepository.save(testEntity);
    }

    public void update(Long id, String name, Integer age) {
        TestEntity testEntity = testRepository.findById(id).get();
        testEntity.changeNameAndAge(name, age);
        testRepository.save(testEntity);
    }

    public void delete(Long id) {
        testRepository.deleteById(id);
    }

    public List<TestEntity> findAll() {
        return testRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }
}
