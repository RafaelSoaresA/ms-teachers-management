package com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.mapper;

import com.example.ms.teachers.management.core.port.out.dto.TeacherListDtoOutput;
import com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.dto.Course;
import com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.dto.Instructor;
import com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.dto.InstructorList;
import com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.dto.Wage;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InstructorListToTeacherListOutputMapperTest {

    private InstructorListToTeacherListOutputMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new InstructorListToTeacherListOutputMapper();
    }

    @Test
    @DisplayName("Deve mapear lista de instrutores para lista de professores")
    void responseGetTeacherListMapper_withValidInstructorList_shouldMapToTeacherList() {
        Instructor instructor = new Instructor();
        Wage wage = new Wage();
        wage.setTotal(1000.0f);
        instructor.setWage(wage);

        Course course = new Course();
        course.setTitle("Mathematics");
        instructor.setCourse(course);

        InstructorList instructorList = new InstructorList();
        instructorList.setInstructors(List.of(instructor));

        TeacherListDtoOutput result = mapper.responseGetTeacherListMapper(instructorList);

        assertNotNull(result);
        assertEquals(1, result.getTeachers().size());
    }

    @Test
    @DisplayName("Deve retornar lista de professores vazia quando lista de instrutores for nula")
    void responseGetTeacherListMapper_withNullInstructorList_shouldReturnEmptyTeacherList() {
        InstructorList instructorList = new InstructorList();
        instructorList.setInstructors(null);

        TeacherListDtoOutput result = mapper.responseGetTeacherListMapper(instructorList);

        assertNotNull(result);
        assertTrue(CollectionUtils.isEmpty(result.getTeachers()));
    }

    @Test
    @DisplayName("Deve retornar lista de professores vazia quando lista de instrutores estiver vazia")
    void responseGetTeacherListMapper_withEmptyInstructorList_shouldReturnEmptyTeacherList() {
        InstructorList instructorList = new InstructorList();
        instructorList.setInstructors(Collections.emptyList());

        TeacherListDtoOutput result = mapper.responseGetTeacherListMapper(instructorList);

        assertNotNull(result);
        assertTrue(CollectionUtils.isEmpty(result.getTeachers()));
    }

}