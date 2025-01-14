package com.example.ms.teachers.management.api.rest;

import com.example.ms.teachers.management.core.port.in.TeacherPortIn;
import com.example.ms.teachers.management.core.port.in.dto.TeacherDto;
import com.example.ms.teachers.management.core.port.in.dto.TeacherListDto;
import com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.dto.Instructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TeachersController {

    private final TeacherPortIn teacherService;
    @GetMapping(value = "/teachers")
    public ResponseEntity<TeacherListDto> getTeachers(
            @RequestHeader(value = "Authorization") String authorization,
            @RequestHeader(value = "origin") String origin
    ){
        return ResponseEntity.ok(teacherService.listTeacher(authorization, origin));
    }

    @GetMapping(value = "/teachers/{id}")
    public ResponseEntity<TeacherDto> getTeacherById(
            @RequestHeader(value = "Authorization") String authorization,
            @RequestHeader(value = "origin") String origin,
            @PathVariable Integer id
    ){
        return ResponseEntity.ok(teacherService.getTeacherById(authorization, origin, id));
    }

    @PostMapping(value = "/teachers")
    public ResponseEntity<TeacherDto> createTeacher(
            @RequestHeader(value = "Authorization") String authorization,
            @RequestHeader(value = "origin") String origin,
            @RequestBody Instructor instructor
            ){
        return  ResponseEntity.ok(teacherService.createTeacher(authorization, origin, instructor));
    }
}
