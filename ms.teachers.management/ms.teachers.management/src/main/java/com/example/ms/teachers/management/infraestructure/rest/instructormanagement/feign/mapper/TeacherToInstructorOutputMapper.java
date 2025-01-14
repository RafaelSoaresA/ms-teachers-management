package com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.mapper;

import com.example.ms.teachers.management.core.port.out.dto.TeacherDtoOutput;
import com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.dto.Course;
import com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.dto.Instructor;
import com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.dto.Wage;
import org.springframework.stereotype.Component;

@Component
public class TeacherToInstructorOutputMapper {
    public Instructor requestCreateTeacher(TeacherDtoOutput teacherDtoOutput){
        Instructor instructor = new Instructor();

        if(teacherDtoOutput != null){
            instructor.setId(teacherDtoOutput.getId());
            instructor.setFullName(teacherDtoOutput.getName());

            Course course = new Course();
            course.setId(teacherDtoOutput.getId());
            course.setTitle(teacherDtoOutput.getName());

            Wage wage = new Wage();
            wage.setCurrency(teacherDtoOutput.getSalary().getCurrency());
            wage.setTotal(teacherDtoOutput.getSalary().getAmount());

            instructor.setCourse(course);
            instructor.setWage(wage);
            return instructor;
        }

        return instructor;
    }
}
