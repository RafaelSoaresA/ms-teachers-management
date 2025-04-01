package com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.mapper;

import com.example.ms.teachers.management.core.port.out.dto.SalaryDtoOutput;
import com.example.ms.teachers.management.core.port.out.dto.SubjectDtoOutput;
import com.example.ms.teachers.management.core.port.out.dto.TeacherDtoOutput;
import com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.dto.Instructor;
import org.springframework.stereotype.Component;

@Component
public class InstructorToTeacherOutputMapper {
    public TeacherDtoOutput responseGetTeacher(Instructor instructor) {
        TeacherDtoOutput teacherDtoOutput = new TeacherDtoOutput();

        if(instructor != null) {

            teacherDtoOutput.setId(instructor.getId());
            teacherDtoOutput.setName(instructor.getFullName());

            SalaryDtoOutput salaryDtoOutput = new SalaryDtoOutput();
            salaryDtoOutput.setAmount(instructor.getWage().getTotal());
            salaryDtoOutput.setCurrency(instructor.getWage().getCurrency());

            SubjectDtoOutput subjectDtoOutput = new SubjectDtoOutput();
            subjectDtoOutput.setId(instructor.getId());
            subjectDtoOutput.setName(instructor.getCourse().getTitle());

            teacherDtoOutput.setSalary(salaryDtoOutput);
            teacherDtoOutput.setSubject(subjectDtoOutput);
            return teacherDtoOutput;
        }
        return teacherDtoOutput;
    }
}
