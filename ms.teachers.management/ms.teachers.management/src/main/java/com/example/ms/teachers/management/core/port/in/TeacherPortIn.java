package com.example.ms.teachers.management.core.port.in;

import com.example.ms.teachers.management.core.port.in.dto.SalaryDto;
import com.example.ms.teachers.management.core.port.in.dto.SubjectDto;
import com.example.ms.teachers.management.core.port.in.dto.TeacherDto;
import com.example.ms.teachers.management.core.port.in.dto.TeacherListDto;
import com.example.ms.teachers.management.infraestructure.rest.instructormanagement.feign.dto.Instructor;

public interface TeacherPortIn {
    TeacherListDto listTeacher(String authorization, String origin);
    TeacherDto getTeacherById(String authorization, String origin, Integer id);
    TeacherDto createTeacher(String authorization,
                             String origin,
                             Instructor instructor);
}
