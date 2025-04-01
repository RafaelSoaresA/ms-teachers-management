package com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign;

import com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.dto.Instructor;
import com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.dto.InstructorList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "teacher-management", fallbackFactory = FallbackInstructorManagement.class)
public interface InstructorManagementFeign {

    @GetMapping(value = "/instructors", consumes = "application/json; charset=utf-8")
    InstructorList getInstructor(
            @RequestHeader(value = "Authorization") String authorization,
            @RequestHeader(value = "origin") String origin
    );

    @GetMapping(value = "/instructors/{id}", consumes = "application/json; charset=utf-8")
    Instructor getInstructorById(
            @RequestHeader(value = "Authorization") String authorization,
            @RequestHeader(value = "origin") String origin,
            @PathVariable Integer id
    );

    @PostMapping(value = "/instructors", consumes = "application/json; charset=utf-8")
    Instructor createInstructor(
            @RequestBody Instructor instructor
    );

    @PutMapping(value = "/instructors/{id}", consumes = "application/json; charset=utf-8")
    Instructor updateInstructor(
            @RequestHeader(value = "Authorization") String authorization,
            @RequestHeader(value = "origin") String origin,
            @RequestBody Instructor instructor,
            @PathVariable Integer id
    );

    @DeleteMapping(value = "/instructors/{id}")
    Instructor deleteInstructor(
            @RequestHeader(value = "Authorization") String authorization,
            @RequestHeader(value = "origin") String origin,
            @PathVariable Integer id
    );
}