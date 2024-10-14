package org.example.jpacrud.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.jpacrud.entity.TestEntity;
import org.example.jpacrud.service.TestService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Log4j2
public class TestApi {
    private final TestService testService;

    @GetMapping("/test/create")
    public void createTest() {
        testService.create("haneol", 10);
    }

    @PostMapping("/test/create")
    public ResponseEntity<?> postCreate(@RequestBody CreateTestRequest request) {
        testService.create(request.getName(), request.getAge());

        return ResponseEntity.ok().build();
    }

    @PutMapping("test/update")
    public ResponseEntity<?> putUpdate(@RequestBody TestEntity request) {
        testService.update(request.getId(), request.getName(), request.getAge());

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("test/{id}/delete")
    public ResponseEntity<?> deleteTest(@PathVariable("id") Long id) {
        testService.delete(id);

        return ResponseEntity.ok().build();
    }

    @GetMapping("test")
    public ResponseEntity<?> getAllTest() {
        return ResponseEntity.ok(testService.findAll());
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class CreateTestRequest {
        private String name;
        private Integer age;
    }
}

