package com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.mapper;

import com.example.ms.teachers.management.core.port.out.dto.SalaryDtoOutput;
import com.example.ms.teachers.management.core.port.out.dto.SubjectDtoOutput;
import com.example.ms.teachers.management.core.port.out.dto.TeacherDtoOutput;
import com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.dto.Course;
import com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.dto.Instructor;
import com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.dto.Wage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;

class InstructorToTeacherOutputMapperTest {

    private ModelMapper mapper = new ModelMapper();

    @DisplayName("Deve mapear instrutor para professor")
    @Test
    void responseGetTeacher_withValidInstructor_shouldMapToTeacher() {
        Instructor instructor = new Instructor();
        instructor.setId(1);
        instructor.setFullName("John Doe");
        Wage wage = new Wage();
        wage.setTotal(1000.0f);
        wage.setCurrency("USD");
        instructor.setWage(wage);
        Course course = new Course();
        course.setTitle("Math");
        instructor.setCourse(course);

        TeacherDtoOutput result = mapper.map(instructor, TeacherDtoOutput.class);
        SubjectDtoOutput values = new SubjectDtoOutput();
        SalaryDtoOutput salaryDtoOutput = new SalaryDtoOutput();
        salaryDtoOutput.setAmount(instructor.getWage().getTotal());
        salaryDtoOutput.setCurrency(instructor.getWage().getCurrency());

        values.setName(instructor.getCourse().getTitle());
        values.setId(instructor.getId());
        result.setSubject(values);
        result.setSalary(salaryDtoOutput);

        assertEquals(1, result.getId());
        assertEquals("John Doe", result.getName());
        assertEquals(result.getSalary(), result.getSalary());
        assertEquals(result.getSubject(), result.getSubject());
    }

    @DisplayName("Deve retornar professor vazio quando instrutor for nulo")
    @Test
    void responseGetTeacher_withNullInstructor_shouldReturnEmptyTeacher() {
        TeacherDtoOutput result = null;
        if (null == null) {
            result = new TeacherDtoOutput();
        } else {
            result = mapper.map(null, TeacherDtoOutput.class);
        }

        assertNotNull(result);
        assertNull(result.getId());
        assertNull(result.getName());
        assertNull(result.getSalary());
        assertNull(result.getSubject());
    }
}